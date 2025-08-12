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

    // --------- Register API ---------
    @PostMapping("/register") 
    public String register(@RequestBody User user) {
        // controller will take data from request body (user) and send to service
        String result = authService.registerUser(user); // call service logic
        return result; // return response as plain string
    }

    // --------- Login API ---------
    @PostMapping("/login") // maps POST request to /auth/login
    public String login(@RequestBody User user) {
        String email = user.getEmail();         // get email from input
        String password = user.getPassword();   // get password from input

        String result = authService.loginUser(email, password); // call login logic
        return result;
    }


    
}
