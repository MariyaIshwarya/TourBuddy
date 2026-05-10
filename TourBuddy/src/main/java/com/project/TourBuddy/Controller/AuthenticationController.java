package com.project.TourBuddy.Controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.project.TourBuddy.Dto.*;
import com.project.TourBuddy.Entity.Role;
import com.project.TourBuddy.Entity.User;
import com.project.TourBuddy.Repository.RoleRepository;
import com.project.TourBuddy.Repository.UserRepository;
import com.project.TourBuddy.security.JwtUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
                )
            );

            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (user.getVisaExpiryDate() != null &&
                user.getVisaExpiryDate().isBefore(LocalDate.now())) {

                user.setStatus("INACTIVE");
                userRepository.save(user);

                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new LoginResponseDto("Visa expired"));
            }

            String token = jwtUtil.generateAccessToken(user);

            String roles = user.getUserroles()
                    .stream()
                    .map(Role::getName)
                    .collect(Collectors.joining(","));
            
            return ResponseEntity.ok(new LoginResponseDto(token, roles));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponseDto("Invalid credentials"));
        }
    }

    // ---------------- TOURIST SIGNUP ----------------
    @PostMapping("/tourist/signup")
    public ResponseEntity<SignupResponseDto> touristSignup(
            @RequestBody @Valid TouristSignupDto request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new SignupResponseDto("Email already exists", null, null, null));
        }

        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPassportNumber(request.getPassportNumber());
        user.setVisaNumber(request.getVisaNumber());
        user.setVisaExpiryDate(request.getVisaExpiryDate());
        user.setNationality(request.getNationality());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setStatus("ACTIVE");
        user.setTouristId("TST" + UUID.randomUUID().toString().substring(0, 8));

        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setUserroles(Arrays.asList(role));
        userRepository.save(user);

        String token = jwtUtil.generateAccessToken(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SignupResponseDto(
                        "Tourist registered",
                        user.getTouristId(),
                        token,
                        "TOURIST"
                ));
    }

    // ---------------- ADMIN SIGNUP ----------------
    @PostMapping("/admin/signup")
    public ResponseEntity<SignupResponseDto> adminSignup(
            @RequestBody @Valid AdminSignupDto request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new SignupResponseDto("Email already exists",null ,null, null));
        }

        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setStatus("ACTIVE");

        Role role = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setUserroles(Arrays.asList(role));
        userRepository.save(user);

        String token = jwtUtil.generateAccessToken(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SignupResponseDto(
                        "Admin registered",
                        null,
                        token,
                        "ADMIN"
                ));
    }
}
