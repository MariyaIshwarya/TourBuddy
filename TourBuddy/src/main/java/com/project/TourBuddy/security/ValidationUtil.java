//package com.project.TourBuddy.security;
//
//import java.time.LocalDate;
//import java.util.regex.Pattern;
//
//public class ValidationUtil {
//
//    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
//    private static final Pattern PASSPORT_PATTERN = Pattern.compile("^[A-Z0-9]{6,12}$");
//    private static final Pattern VISA_PATTERN = Pattern.compile("^[A-Z0-9]{8,15}$");
//
//    public static boolean isValidEmail(String email) {
//        return email != null && EMAIL_PATTERN.matcher(email).matches();
//    }
//
//    public static boolean isValidPassport(String passport) {
//        return passport != null && PASSPORT_PATTERN.matcher(passport).matches();
//    }
//
//    public static boolean isValidVisa(String visa) {
//        return visa != null && VISA_PATTERN.matcher(visa).matches();
//    }
//
//    public static boolean isVisaValid(LocalDate expiryDate) {
//        return expiryDate != null && expiryDate.isAfter(LocalDate.now());
//    }
//}