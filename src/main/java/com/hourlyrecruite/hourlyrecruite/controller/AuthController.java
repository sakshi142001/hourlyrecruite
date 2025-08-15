package com.hourlyrecruite.hourlyrecruite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hourlyrecruite.hourlyrecruite.service.AuthService;

import com.hourlyrecruite.hourlyrecruite.model.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        String result = authService.registerUser(user);
        return result;
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        String email = user.getEmail();
        String password = user.getPassword();

        String result = authService.loginUser(email, password);
        return result;
    }

}
