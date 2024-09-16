package group5.eeet2580_project.controller;

import group5.eeet2580_project.dto.request.DeleteUserRequest;
import group5.eeet2580_project.dto.request.SearchUserRequest;
import group5.eeet2580_project.dto.request.UpdateUserRequest;
import group5.eeet2580_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("v1/users")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/search")
    public ResponseEntity<?> searchUser(@Valid @RequestBody SearchUserRequest request) {
        return userService.searchUser(request);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUserByToken(HttpServletRequest httpRequest) {
        return userService.getUserByToken(httpRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@Valid @RequestBody DeleteUserRequest request, HttpServletRequest httpRequest) {
        return userService.deleteUser(request, httpRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        return userService.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest request, HttpServletRequest httpRequest) {
        return userService.updateUser(id, request, httpRequest);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(HttpServletRequest httpRequest) {
        return userService.getAllUsers(httpRequest);
    }

    @GetMapping("/sprayers")
    public ResponseEntity<?> getAllSprayers(HttpServletRequest httpRequest) {
        return userService.getAllSprayers();
    }
}