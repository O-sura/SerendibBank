package com.banking.manager;


import com.banking.domain.User;
import com.banking.notification.NotificationService;

public class OTPManager {
    private NotificationService notificationService;

    public String generateOTP() {
        System.out.println("Generating OTP...");
        return "123456";
    }

    public boolean validateOTP(String otp) {
        System.out.println("Validating OTP: " + otp);
        return otp.equals("123456");
    }

    public void deliverOTP(User user, String otp) {
        System.out.println("Delivering OTP to user: " + user.getUsername());
        notificationService.send(user.getMobileNumber(), "Your OTP is: " + otp);
    }
}
