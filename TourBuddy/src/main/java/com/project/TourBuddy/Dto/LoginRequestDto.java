package com.project.TourBuddy.Dto;


//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//public class LoginRequestDto {
//
//    @Email
//    @NotBlank
//    private String email;
//
//    @NotBlank
//    private String password;
//}


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
    private String password;

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
}
