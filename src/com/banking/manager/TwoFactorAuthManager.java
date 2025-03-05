package com.banking.manager;

import com.banking.domain.User;
import com.banking.notification.NotificationService;

public class TwoFactorAuthManager {
    private OTPManager otpManager;
    private NotificationService notificationService;

    public TwoFactorAuthManager(OTPManager otpManager, NotificationService notificationService) {
        this.otpManager = otpManager;
        this.notificationService = notificationService;
    }

    public void initiateOTPVerification(User user) {
        String otp = otpManager.generateOTP(user.getUsername());
        otpManager.deliverOTP(user, otp);
        System.out.println("OTP sent to user: " + user.getUsername());
    }

    public boolean verifyOTP(User user, String otp) {
        boolean isValid = otpManager.validateOTP(user.getUsername(),otp);
        if (isValid) {
            System.out.println("OTP verification successful.");
        } else {
            System.out.println("OTP verification failed.");
        }
        return isValid;
    }
}
