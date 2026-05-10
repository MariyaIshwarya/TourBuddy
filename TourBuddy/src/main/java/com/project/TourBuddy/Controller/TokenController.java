package com.project.TourBuddy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.TourBuddy.Entity.User;
import com.project.TourBuddy.Repository.UserRepository;
import com.project.TourBuddy.security.JwtUtil;


@RestController
@RequestMapping("/api/token")
public class TokenController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/generate")
    public ResponseEntity<String> generateToken(@RequestParam String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtUtil.generateAccessToken(user);

        return ResponseEntity.ok(token);
    }
}
