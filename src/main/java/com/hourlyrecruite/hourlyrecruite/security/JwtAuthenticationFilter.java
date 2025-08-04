package com.hourlyrecruite.hourlyrecruite.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hourlyrecruite.hourlyrecruite.repository.UserRepository;
import com.hourlyrecruite.hourlyrecruite.model.*;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

    // ✅ Step 1: Skip JWT check for login/register endpoints
        String path = request.getServletPath();
        if (path.equals("/api/auth/login") || path.equals("/api/auth/register")) {
            filterChain.doFilter(request, response);
            return;
        }


        // Get the Authorization header from the request
        String authHeader = request.getHeader("Authorization");

                String token = null;
                String email = null;
                String role = null;

        // Check if the header is present and starts with "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // Extract token (remove "Bearer ")
            email = jwtUtil.extractEmail(token); // extract email from token
            role = jwtUtil.extractRole(token);
        }

        // If we have an email and no authentication is yet set
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Load user from DB
            User user = userRepository.findByEmail(email).orElse(null);

            // If token is valid for this user
            if (user != null && jwtUtil.isTokenValid(token, email)) {

                 //Use role from JWT to create authorities
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(user, null, Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role)));

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }

}
