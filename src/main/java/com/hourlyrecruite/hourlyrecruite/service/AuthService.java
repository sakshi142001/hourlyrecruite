package com.hourlyrecruite.hourlyrecruite.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import com.hourlyrecruite.hourlyrecruite.security.*;
import com.hourlyrecruite.hourlyrecruite.model.User;
import com.hourlyrecruite.hourlyrecruite.repository.UserRepository;
import com.hourlyrecruite.hourlyrecruite.security.JwtUtil;

@Service
public class AuthService {
   @Autowired
    private  UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public String registerUser(User user){
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if(existingUser.isPresent()){
            return "Email already registered";
        }

        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
// Set default role if not set
if (user.getRole() == null) {
            user.setRole(Role.CANDIDATE); // default role
        }

    // Save user
    userRepository.save(user);
      return "User registered successfully";
    
    }


        // LOGIN
    // =============================
    public String loginUser(String email, String rawPassword) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isEmpty()){
            return "Email not found" ;
        }
        User user = userOptional.get();

        //compare encrypted password
        if(!passwordEncoder.matches(rawPassword, user.getPassword())){
            return "Incorrect Password" ;
        }

        //Generate token using email as subject
        String token = jwtUtil.generateToken(user);
        return " login seccess. Token : " + token;
        
    }

    
}


    


