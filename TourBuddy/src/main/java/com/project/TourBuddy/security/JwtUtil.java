package com.project.TourBuddy.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.project.TourBuddy.Entity.Role;
import com.project.TourBuddy.Entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiry; // in ms

    private Key key;

    // 🔹 Initialize secret key
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateAccessToken(User user) {

        Map<String, Object> claims = new HashMap<>();

        // MANY TO MANY → roles list
        claims.put(
            "roles",
            user.getUserroles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toList())
        );

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(
                        new Date(System.currentTimeMillis() + accessTokenExpiry)
                )
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔹 Validate token
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return email.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    // 🔹 Check expiration
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // 🔹 Extract expiration
    public Date extractExpiration(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    // 🔹 Extract email
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}