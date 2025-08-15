package com.hourlyrecruite.hourlyrecruite.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendEmail(String to, String subject, String message) {
        System.out.println("Email sent to " + to + ": " + subject + " - " + message);
    }

    public void sendSMS(String to, String message) {
        System.out.println("SMS sent to " + to + ": " + message);
    }

    public void sendWhatsApp(String to, String message) {
        System.out.println("WhatsApp message sent to " + to + ": " + message);
    }

    public void notifyCandidate(String email, String phone, String message) {
        sendEmail(email, "Interview Notification", message);
        sendSMS(phone, message);
        sendWhatsApp(phone, message);
    }

}
