package group5.eeet2580_project.controller;

import group5.eeet2580_project.dto.req.DeleteUserRequest;
import group5.eeet2580_project.dto.req.SearchUserRequest;
import group5.eeet2580_project.dto.req.UpdateUserRequest;
import group5.eeet2580_project.dto.resp.UserResponse;
import group5.eeet2580_project.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/users")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchUser(@Valid @RequestBody SearchUserRequest searchUserRequest) {
        return userService.searchUser(searchUserRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@Valid @RequestBody DeleteUserRequest deleteUserRequest, HttpServletRequest request) {
        return userService.deleteUser(deleteUserRequest, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        return userService.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest updateUserRequest, HttpServletRequest request) {
        return userService.updateUser(id, updateUserRequest, request);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}