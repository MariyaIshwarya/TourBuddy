package com.project.TourBuddy.Dto;

import lombok.Data;

@Data
public class SignupResponseDto {
    private String message;
    private String Id;
    private String token;
    private String role;

    public SignupResponseDto(String message,String Id, String token, String role) {
    	this.message = message;
    	this.Id = Id;
        this.token = token;
        this.role = role;
    }

//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public String getTouristId() {
//        return touristId;
//    }
//
//    public void setTouristId(String touristId) {
//        this.touristId = touristId;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
}