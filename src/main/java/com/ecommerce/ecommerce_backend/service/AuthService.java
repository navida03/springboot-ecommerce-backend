package com.ecommerce.ecommerce_backend.service;


import com.ecommerce.ecommerce_backend.dto.LoginRequest;
import com.ecommerce.ecommerce_backend.dto.RegisterRequest;
import com.ecommerce.ecommerce_backend.model.User;
import com.ecommerce.ecommerce_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register user
    public User registerUser(RegisterRequest request) {

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return userRepository.save(user);
    }

    // Login user
    public String loginUser(LoginRequest request) {

        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if (user.isPresent() && user.get().getPassword().equals(request.getPassword())) {
            return "Login Successful";
        }

        return "Invalid Email or Password";
    }

    // Get user by id
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
