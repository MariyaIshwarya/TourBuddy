package com.project.TourBuddy.Dto;


import lombok.Data;
@Data
public class LoginResponseDto {
	private String token;
	private String role;
	
	private String message;
	private boolean onboardingCompleted;
	private String touristId;

	public LoginResponseDto(String token, String role) {
		this.token = token;
		this.role = role;
	}
	
	public LoginResponseDto(String message) {
		this.message = message;
	}

	public LoginResponseDto(boolean onboardingCompleted, String touristId) {
		this.onboardingCompleted = onboardingCompleted;
		this.touristId = touristId;
		
	}

}
	
////	public String getToken() {
////		return token;
////	}
////	
////	public String getRole() {
////		return role;
////	}
////	
////	public String getMessage() {
////		return message;
////	}
//}