package com.banking.manager;

import java.util.List;

import com.banking.domain.User;
import com.banking.facade.OnboardingFacade.Method;
import com.banking.notification.NotificationService;
import com.banking.manager.UserProfileManager;

public class TwoFactorAuthManager {
    private OTPManager otpManager;
    private NotificationService notificationService;
    private UserProfileManager profileManager;

    public TwoFactorAuthManager(OTPManager otpManager, NotificationService notificationService, UserProfileManager profileManager) {
        this.otpManager = otpManager;
        this.notificationService = notificationService;
        this.profileManager = profileManager;
    }

    public void initiateOTPVerification(User user) {
        String otp = otpManager.generateOTP(user.getUsername());
        List<Method> methods = profileManager.getOTPPreference(user);
        otpManager.deliverOTP(user.getUsername(), otp, methods);
       
    }

    public boolean verifyOTP(User user, String otp) {
        boolean isValid = otpManager.validateOTP(user.getUsername(),otp);
        if (isValid) {
            System.out.println("Two-Factor authentication OTP verification successful.");
        } else {
            System.out.println("Two-Factor authentication OTP verification failed.");
            return false;
        }
        return true; //Hardcoded just for the sake of implementation :)
    }
}
