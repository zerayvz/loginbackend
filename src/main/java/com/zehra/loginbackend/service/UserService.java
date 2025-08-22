package com.zehra.loginbackend.service;

import com.zehra.loginbackend.model.UserEntity;
import com.zehra.loginbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }
}
