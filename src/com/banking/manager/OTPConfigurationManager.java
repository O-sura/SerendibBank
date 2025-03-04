package com.banking.manager;


import java.util.Arrays;
import java.util.List;

import com.banking.domain.User;

public class OTPConfigurationManager {
    private List<String> availableMethods = Arrays.asList("SMS", "Email", "Authenticator App");

    public List<String> getAvailableMethods() {
        return availableMethods;
    }

    public boolean validateMethod(String method) {
        boolean isValid = availableMethods.contains(method);
        System.out.println("Validating OTP method: " + method + " -> " + isValid);
        return isValid;
    }

    public void setUserPreference(User user, String preferredMethod) {
        user.setPreferredOtpMethod(preferredMethod);
        System.out.println("Set OTP preference for user: " + user.getUsername() + " to " + preferredMethod);
    }

    public String getUserPreference(User user) {
        System.out.println("Fetching OTP preference for user: " + user.getUsername());
        return user.getPreferredOtpMethod();
    }
}
