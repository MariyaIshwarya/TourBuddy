//package com.project.TourBuddy.Schedmail;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.project.TourBuddy.Service.EmailService;
//
//
//
//@Component
//public class EmailScheduler {
//
//	@Autowired
//	private EmailService emailService; // Directly inject the class
//
//	@Scheduled(cron = "0 0/10 * * * ?") // every 10 minutes
//	public void sendScheduledEmail() {
//
//		// List of recipients
//		String[] recipients = { "raochaithanya7@gmail.com"};
//
//		emailService.sendEmail(recipients, "Scheduled Email from Spring Boot",
//				"Hello! This is a scheduled email sent automatically every 10 minutes.");
//	}
//}