package com.project.TourBuddy.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.TourBuddy.Entity.User;
import com.project.TourBuddy.Repository.UserRepository;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

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
        
        long totalTourists = userRepository.count() - 1; // Exclude admin
        return ResponseEntity.ok("Welcome to Admin Dashboard, " + user.getUserName() + 
                                "! Total Tourists: " + totalTourists);
    }

    @GetMapping("/tourists")
    public ResponseEntity<List<User>> getAllTourists() {
        List<User> tourists = userRepository.findByRole_Name("USER");
        // Remove passwords from response
        tourists.forEach(user -> user.setPassword(null));
        return ResponseEntity.ok(tourists);
    }
}
