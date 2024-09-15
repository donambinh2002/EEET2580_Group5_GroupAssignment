package group5.eeet2580_project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import group5.eeet2580_project.common.Constants;
import group5.eeet2580_project.config.jwt.JwtUtil;
import group5.eeet2580_project.dto.request.UpdateUserRequest;
import group5.eeet2580_project.entity.User;
import group5.eeet2580_project.dto.request.DeleteUserRequest;
import group5.eeet2580_project.dto.request.SearchUserRequest;
import group5.eeet2580_project.dto.response.MessageResponse;
import group5.eeet2580_project.dto.response.UserResponse;
import group5.eeet2580_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JedisPool jedisPool;
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;

    public ResponseEntity<?> searchUser(SearchUserRequest request) {
        Optional<User> user = userRepository.findByUsernameOrEmail(request.getCredential(), request.getCredential());

        if (user.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("User with credential: " + request.getCredential() + " not found!"));
        }

        return ResponseEntity.ok(new UserResponse(user.get()));
    }

    public ResponseEntity<?> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("User with id: " + id + " not found!"));
        }

        return ResponseEntity.ok(new UserResponse(user.get()));
    }

    public ResponseEntity<?> getAllUsers(HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader("Authorization").substring(7);
        List<String> roles = jwtUtil.extractRoles(token);

        if (!roles.contains(Constants.ROLE_KEYS.RECEPTIONIST)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("You are not authorized to view all users"));
        }

        List<User> users = userRepository.findAll();

        List<UserResponse> responses = users.stream()
                .map(UserResponse::new)
                .toList();

        return ResponseEntity.ok(responses);
    }

    public ResponseEntity<?> getUserByToken(HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader("Authorization").substring(7);
        String username = jwtUtil.extractUsername(token);

        try (Jedis jedis = jedisPool.getResource()) {
            String cachedUser = jedis.get("user:" + username + token);
            if (cachedUser == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("User not logged in"));
            }
            User user = objectMapper.readValue(cachedUser, User.class);
            return ResponseEntity.ok(new UserResponse(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Unable to retrieve user from cache"));
        }
    }

    @Transactional
    public ResponseEntity<?> updateUser(Long id, UpdateUserRequest request, HttpServletRequest httpRequest) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found!"));
        }

        User user = userOptional.get();

        if(request.getFullName() != null){
            user.setFullName(request.getFullName());
        }

        if(request.getAddress() != null){
            user.setAddress(request.getAddress());
        }

        if(request.getPassword() != null){
            user.setPassword(request.getPassword());
        }

        userRepository.save(user);

        String token = httpRequest.getHeader("Authorization").substring(7);
        try (Jedis jedis = jedisPool.getResource()) {
            String cachedUser = jedis.get("user:" + token);
            if (cachedUser != null) {
                jedis.setex("user:" + token, 3600, objectMapper.writeValueAsString(user));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Unable to update user cache"));
        }
        return ResponseEntity.ok(new UserResponse(user));
    }

    @Transactional
    public ResponseEntity<?> deleteUser(DeleteUserRequest request, HttpServletRequest httpRequest) {
        Optional<User> userOptional = userRepository.findByUsernameOrEmail(request.getCredential(), request.getCredential());

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("User not found!"));
        }

        User user = userOptional.get();
        userRepository.delete(user);

        try (Jedis jedis = jedisPool.getResource()) {
            Set<String> keys = jedis.keys("user:" + user.getUsername() + "*");
            if (!keys.isEmpty()) {
                jedis.del(keys.toArray(new String[0]));
            }
        }

        return ResponseEntity.ok(new MessageResponse("Successfully deleted User " + request.getCredential()));
    }

    @Transactional
    public ResponseEntity<?> deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("User not found!"));
        }

        userRepository.delete(user.get());

        return ResponseEntity.ok(new MessageResponse("Successfully deleted User " + id));
    }

    public ResponseEntity<?> getAllSprayers() {
        List<User> users = userRepository.findAllByRoles(Constants.ROLE_KEYS.SPRAYER);

        List<UserResponse> responses = users.stream()
                .map(UserResponse::new)
                .toList();

        return ResponseEntity.ok(responses);
    }
}
