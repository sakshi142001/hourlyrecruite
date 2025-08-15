package com.hourlyrecruite.hourlyrecruite.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hourlyrecruite.hourlyrecruite.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    //@Value("${JWT_SECRET}")
    //private String SECRET_KEY;
    private String SECRET_KEY = "mysecret123";
    private long EXPIRATION_TIME = 10 * 60 * 60 * 1000;

    public String generateToken(User user) {

        long currentTimeMillis = System.currentTimeMillis();

        long expirationTime = currentTimeMillis + EXPIRATION_TIME;

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(expirationTime))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractEmail(String token) {
        Claims claims = getAllClaims(token);
        String email = claims.getSubject();
        return email;
    }

    public String extractRole(String token) {
        return getAllClaims(token).get("role", String.class);
    }

    public boolean isTokenValid(String token, String userEmail) {
        String extractedEmail = extractEmail(token);

        boolean sameEmail = extractedEmail != null && extractedEmail.equals(userEmail);
        boolean notExpired = !isTokenExpired(token);

        return sameEmail && notExpired;

    }

    private boolean isTokenExpired(String token) {
        Claims claims = getAllClaims(token);
        Date expirationDate = claims.getExpiration();

        Date currentDate = new Date();
        return expirationDate.before(currentDate);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

}
