package com.project.TourBuddy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.TourBuddy.Entity.User;
import com.project.TourBuddy.Repository.UserRepository;


@RestController
@RequestMapping("/api/tourist")
public class TouristController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/dashboard")
    public ResponseEntity<String> getDashboard(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        
        User user = userRepository.findByEmail(authentication.getName()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }
        
        return ResponseEntity.ok("Welcome to Tourist Safety Dashboard, " + user.getUserName() + 
                                "! Tourist ID: " + user.getTouristId());
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(401).build();
        }
        
        User user = userRepository.findByEmail(authentication.getName()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(404).build();
        }
        
        // Remove password from response
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }
}