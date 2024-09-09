package group5.eeet2580_project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import group5.eeet2580_project.dto.request.UserRequest;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JedisPool jedisPool;
    private final ObjectMapper objectMapper;

    public ResponseEntity<?> searchUser(SearchUserRequest request) {
        Optional<User> user = userRepository.findByUsernameOrEmail(request.getCredential(), request.getCredential());

        if (user.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("User not found!"));
        }

        return ResponseEntity.ok(new UserResponse(user.get()));
    }

    public ResponseEntity<?> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("User not found!"));
        }

        return ResponseEntity.ok(new UserResponse(user.get()));
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public ResponseEntity<?> updateUser(Long id, UserRequest request, HttpServletRequest httpRequest) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found!"));
        }

        User user = userOptional.get();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.getRoles().add(request.getRole().toUpperCase());
        userRepository.save(user);

        String token = httpRequest.getHeader("Authorization").substring(7);
        try (Jedis jedis = jedisPool.getResource()) {
            String cachedUser = jedis.get("user:" + token);
            if (cachedUser != null) {
                jedis.setex("user:" + token, 3600, objectMapper.writeValueAsString(user));  // Update cache if exists
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

        return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
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

        return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
    }
}
