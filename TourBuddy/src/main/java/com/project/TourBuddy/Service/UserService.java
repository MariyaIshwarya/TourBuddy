package com.project.TourBuddy.Service;


import com.project.TourBuddy.Dto.LoginRequestDto;
import com.project.TourBuddy.Dto.LoginResponseDto;
import com.project.TourBuddy.Entity.User;

public interface UserService {

    LoginResponseDto login(LoginRequestDto request);
        User register(User user);

}
