//package com.project.TourBuddy.ServiceImplementation;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//import com.project.TourBuddy.Service.EmailService;
//
//
//@Service
//public class EmailServiceImplmentation implements EmailService {
//	@Autowired
//	private JavaMailSender mailSender;
//
//	@Override
//	public void sendEmail(String[] to, String subject, String text) {
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setTo(to);
//		message.setSubject(subject);
//		message.setText(text);
//		// Spring will automatically use spring.mail.username as sender
//		mailSender.send(message);
//		System.out.println("Email sent to " + to);
//	}
//}