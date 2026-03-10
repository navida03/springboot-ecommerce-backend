package com.ecommerce.ecommerce_backend.controller;


import com.ecommerce.ecommerce_backend.dto.LoginRequest;
import com.ecommerce.ecommerce_backend.dto.RegisterRequest;
import com.ecommerce.ecommerce_backend.model.User;
import com.ecommerce.ecommerce_backend.service.AuthService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Register User
    @PostMapping("/register")
    public User registerUser(@RequestBody RegisterRequest request) {
        return authService.registerUser(request);
    }

    // Login User
    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest request) {
        return authService.loginUser(request);
    }
}
