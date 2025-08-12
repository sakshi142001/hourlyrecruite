package com.hourlyrecruite.hourlyrecruite.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.hourlyrecruite.hourlyrecruite.service.CustomUserDetailsService;


 @EnableWebSecurity //for @preAuthorize to work
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // 1. Password encoder (used to encrypt/decrypt passwords)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Using BCrypt encryption
    }

    // 2. Authentication Provider: connects the user service and password encoder
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        // Set how to load user data
        provider.setUserDetailsService(customUserDetailsService);

        // Set how to compare passwords
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    // 3. AuthenticationManager: manually create authentication manager from config
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

// 4. Security Filter Chain: set rules for which APIs are public or secured
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()); // Disable CSRF for testing API with Postman

        http
            .authorizeHttpRequests(auth -> {
                try {
                    // Public APIs (no token needed)
                    auth.requestMatchers("/api/auth/register", "/api/auth/login").permitAll()
                    // Job-related APIs (only for ADMIN and RECRUITER)
                    .requestMatchers("/admin/").hasRole("ADMIN")
                    .requestMatchers("/api/jobs/").hasAnyRole("ADMIN", "RECRUITER")
                    .requestMatchers("/candidate/").hasRole("CANDIDATE")
                    .requestMatchers("/recruiter/").hasRole("RECRUITER")


                    // All other APIs need token
                    .anyRequest().authenticated();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // Don’t store session (we use token-based login)
        http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Set the authentication filter before Spring's built-in filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    
}
