package group5.eeet2580_project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import group5.eeet2580_project.config.jwt.JwtUtil;
import group5.eeet2580_project.entity.User;
import group5.eeet2580_project.dto.request.LoginUserRequest;
import group5.eeet2580_project.dto.request.UserRequest;
import group5.eeet2580_project.dto.response.AuthResponse;
import group5.eeet2580_project.dto.response.MessageResponse;
import group5.eeet2580_project.dto.response.UserResponse;
import group5.eeet2580_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    private final JedisPool jedisPool;
    private final ObjectMapper objectMapper;

    private void cacheUser(User user, String accessToken) throws JsonProcessingException {
        try (Jedis jedis = jedisPool.getResource()) {
            // Ensure old cached tokens for this user are deleted
            Set<String> keys = jedis.keys("user:" + user.getUsername() + "*");
            if (!keys.isEmpty()) {
                jedis.del(keys.toArray(new String[0]));
            }
            jedis.setex("user:" + user.getUsername() + accessToken, 3600, objectMapper.writeValueAsString(user));
        }
    }

    @Transactional
    public ResponseEntity<?> registerUser(UserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Username is already taken!"));
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email is already in use!"));
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setFullName(request.getFullName());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.getRoles().add(request.getRole().toUpperCase());

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MessageResponse("User registered successfully!", new UserResponse(user)));
    }

    public ResponseEntity<?> authenticateUser(LoginUserRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getCredential(), request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = userRepository.findByUsernameOrEmail(request.getCredential(), request.getCredential())
                    .orElseThrow(() -> new RuntimeException("User not found!"));

            String accessToken = jwtUtil.generateAccessToken(user.getUsername());
            String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

            cacheUser(user, accessToken);

            return ResponseEntity.ok(new MessageResponse("Authenticated successfully for user " + user.getUsername(), new AuthResponse(accessToken, refreshToken)));
        } catch (AuthenticationException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Unauthorized! Please check your credentials!"));
        }
    }

    @Transactional
    public ResponseEntity<?> refreshToken(Map<String, String> request) throws JsonProcessingException {
        String refreshToken = request.get("refreshToken");
        if (refreshToken == null || !jwtUtil.validateToken(refreshToken, null)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Invalid refresh token"));
        }

        String username = jwtUtil.extractUsername(refreshToken);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found!"));

        String newAccessToken = jwtUtil.generateAccessToken(username);
        String newRefreshToken = jwtUtil.generateRefreshToken(username);

        cacheUser(user, newAccessToken);

        return ResponseEntity.ok(new AuthResponse(newAccessToken, newRefreshToken));
    }


    @Transactional
    public ResponseEntity<?> logout(HttpServletRequest httpRequest) {
        String authorizationHeader = httpRequest.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Invalid access token"));
        }
        String accessToken = authorizationHeader.substring(7);

        if (!jwtUtil.validateToken(accessToken, null)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Invalid access token"));
        }

        String username = jwtUtil.extractUsername(accessToken);

        try (Jedis jedis = jedisPool.getResource()) {
            jedis.del("user:" + username + accessToken);
        }

        return ResponseEntity.ok(new MessageResponse("User logged out successfully"));
    }

    public ResponseEntity<?> validateToken(Map<String, String> request) {
        String token = request.get("token");
        if (token == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Token is missing"));
        }
        try {
            String username = jwtUtil.extractUsername(token);
            if (username == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Invalid token"));
            }

            // Check cache first
            try (Jedis jedis = jedisPool.getResource()) {
                String cachedUser = jedis.get("user:" + username + token);
                if (cachedUser != null) {
                    User user = objectMapper.readValue(cachedUser, User.class);
                    Map<String, Object> response = new HashMap<>();
                    response.put("username", user.getUsername());
                    response.put("roles", user.getRoles());
                    return ResponseEntity.ok(response);
                }
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Expired token"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Invalid token"));
        }
    }
}