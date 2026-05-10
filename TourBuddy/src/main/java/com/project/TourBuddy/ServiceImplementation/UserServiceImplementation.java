package com.project.TourBuddy.ServiceImplementation;

import org.springframework.stereotype.Service;

import com.project.TourBuddy.Dto.LoginRequestDto;
import com.project.TourBuddy.Dto.LoginResponseDto;
import com.project.TourBuddy.Entity.Role;
import com.project.TourBuddy.Entity.User;
import com.project.TourBuddy.Repository.RoleRepository;
import com.project.TourBuddy.Repository.UserRepository;
import com.project.TourBuddy.Service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Override
    public LoginResponseDto login(LoginRequestDto request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Password check (TEMP – later we add encryption)
        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return new LoginResponseDto(
                "Login successful",
//                user.isOnboardingCompleted(),
                user.getTouristId()
        );

    }
    @Override
    public User register(User user) {

        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("ROLE USER NOT FOUND"));

        user.setRole(role);
        user.setOnboardingCompleted(false);

        return userRepository.save(user);
    }
}
