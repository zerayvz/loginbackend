package com.zehra.loginbackend.controller;

import com.zehra.loginbackend.model.UserEntity;
import com.zehra.loginbackend.model.User;
import com.zehra.loginbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public boolean login(@RequestBody User loginUser) {
        UserEntity userEntity = userRepository.findByUsername(loginUser.getUsername());
        return userEntity != null && userEntity.getPassword().equals(loginUser.getPassword());
    }
}
