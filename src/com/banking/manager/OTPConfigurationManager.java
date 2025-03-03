package com.banking.manager;


import com.banking.domain.User;

public class OTPConfigurationManager {
    public void setUserPreference(User user, String preferredMethod) {
        System.out.println("Setting OTP preference for user: " + user.getUsername() + " to " + preferredMethod);
        user.setPreferredOtpMethod(preferredMethod);
    }

    public String getUserPreference(User user) {
        System.out.println("Fetching OTP preference for user: " + user.getUsername());
        return user.getPreferredOtpMethod();
    }
}
