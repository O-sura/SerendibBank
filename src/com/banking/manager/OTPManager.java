package com.banking.manager;


import java.util.HashMap;
import java.util.Map;

import com.banking.domain.User;
import com.banking.notification.NotificationService;

public class OTPManager {
    private Map<String, String> otpStore = new HashMap<>();
    private Map<String, Integer> attemptCounter = new HashMap<>();

    public String generateOTP() {
        String otp = "123456"; // Placeholder logic
        otpStore.put(otp, "user"); // Placeholder user
        return otp;
    }

    public boolean validateOTP(String otp) {
        boolean isValid = otpStore.containsKey(otp);
        System.out.println("OTP validation result: " + isValid);
        return isValid;
    }

    public void deliverOTP(User user, String otp) {
        System.out.println("Delivering OTP to user: " + user.getUsername() + " via " + user.getPreferredOtpMethod());
    }

    public boolean isOTPExpired() {
        System.out.println("Checking if OTP is expired.");
        return false; // Placeholder logic
    }

    public int getAttemptsLeft() {
        System.out.println("Getting OTP attempts left.");
        return 3; // Placeholder logic
    }
}
