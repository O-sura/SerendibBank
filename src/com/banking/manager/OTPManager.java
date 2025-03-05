package com.banking.manager;


import java.util.HashMap;
import java.util.Map;

import com.banking.domain.User;
import com.banking.notification.NotificationService;

public class OTPManager {
    private Map<String, String> otpStore = new HashMap<>();
    private Map<String, Integer> attemptCounter = new HashMap<>();
    private Map<String, Long> otpTimestamps = new HashMap<>();
    private static final int MAX_ATTEMPTS = 3;
    private static final long OTP_EXPIRY_MS = 30000;

    public String generateOTP(String user) {
        String otp = "123456";
        otpStore.put(user, otp);
        otpTimestamps.put(user, System.currentTimeMillis());
        attemptCounter.put(user, 0);
        return otp;
    }

    public boolean validateOTP(String user, String otp) {
        if (isOTPExpired(user)) {
            System.out.println("OTP expired for user: " + user);
            return false;
        }

        if (!otpStore.containsKey(user) || !otpStore.get(user).equals(otp)) {
            int attempts = attemptCounter.getOrDefault(user, 0) + 1;
            attemptCounter.put(user, attempts);
            if (attempts >= MAX_ATTEMPTS) {
                System.out.println("User locked due to excessive OTP failures: " + user);
            }
            return false;
        }

        attemptCounter.put(user, 0);
        return true;
    }

    public void deliverOTP(User user, String otp) {
        System.out.println("Delivering OTP to user: " + user.getUsername() + " via " + user.getPreferredOtpMethod());
    }

    public boolean isOTPExpired(String user) {
        System.out.println("Checking if OTP is expired.");
        return System.currentTimeMillis() - otpTimestamps.getOrDefault(user, 0L) > OTP_EXPIRY_MS;
    
    }

    public int getAttemptsLeft() {
        System.out.println("Getting OTP attempts left.");
        return 3; // Placeholder logic
    }
}
