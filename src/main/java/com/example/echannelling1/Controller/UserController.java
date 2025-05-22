package com.example.echannelling1.Controller;

import com.example.echannelling1.Classes.User;
import com.example.echannelling1.Service.oldUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final oldUserService userService = new oldUserService();

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        
        // Validate input
        if (user.getUsername() == null || user.getUsername().trim().isEmpty() ||
            user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            response.put("message", "Username and password are required");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        
        // Set default role if not provided
        if (user.getRole() == null || user.getRole().trim().isEmpty()) {
            user.setRole("USER");
        }
        
        boolean success = userService.registerUser(user);
        
        if (success) {
            response.put("message", "User registered successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.put("message", "Username already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    // Authenticate a user
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Map<String, String> credentials) {
        Map<String, Object> response = new HashMap<>();
        
        String username = credentials.get("username");
        String password = credentials.get("password");
        
        if (username == null || password == null) {
            response.put("message", "Username and password are required");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        
        Optional<User> user = userService.authenticateUser(username, password);
        
        if (user.isPresent()) {
            User authenticatedUser = user.get();
            // Don't include password in the response
            response.put("username", authenticatedUser.getUsername());
            response.put("email", authenticatedUser.getEmail());
            response.put("fullName", authenticatedUser.getFullName());
            response.put("role", authenticatedUser.getRole());
            response.put("phoneNumber", authenticatedUser.getPhoneNumber());
            response.put("message", "Login successful");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    // Get user profile
    @GetMapping("/{username}")
    public ResponseEntity<Map<String, Object>> getUserProfile(@PathVariable String username) {
        Map<String, Object> response = new HashMap<>();
        
        Optional<User> user = userService.getUserByUsername(username);
        
        if (user.isPresent()) {
            User foundUser = user.get();
            // Don't include password in the response
            response.put("username", foundUser.getUsername());
            response.put("email", foundUser.getEmail());
            response.put("fullName", foundUser.getFullName());
            response.put("role", foundUser.getRole());
            response.put("phoneNumber", foundUser.getPhoneNumber());
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Update user profile
    @PutMapping("/{username}")
    public ResponseEntity<Map<String, String>> updateUser(@PathVariable String username, @RequestBody User updatedUser) {
        Map<String, String> response = new HashMap<>();
        
        boolean success = userService.updateUser(username, updatedUser);
        
        if (success) {
            response.put("message", "User updated successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Delete user
    @DeleteMapping("/{username}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable String username) {
        Map<String, String> response = new HashMap<>();
        
        boolean success = userService.deleteUser(username);
        
        if (success) {
            response.put("message", "User deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
