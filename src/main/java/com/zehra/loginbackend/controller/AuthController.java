package com.zehra.loginbackend.controller;

import com.zehra.loginbackend.dto.LoginRequest;
import com.zehra.loginbackend.model.UserEntity;
import com.zehra.loginbackend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:8080")
public class AuthController {

    private final UserRepository userRepository;

    // Explicit constructor (no Lombok)
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (request.getUsername() == null || request.getPassword() == null) {
            return ResponseEntity.badRequest().body("username and password are required");
        }

        UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElse(null);
        if (user == null) {
            return ResponseEntity.status(401).body("User not found");
        }

        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(401).body("Invalid password");
        }

        return ResponseEntity.ok("Login successful");
    }
}
