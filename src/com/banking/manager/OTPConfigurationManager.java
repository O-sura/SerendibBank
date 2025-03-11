package com.banking.manager;

import java.util.*;
import com.banking.domain.User;
import com.banking.facade.OnboardingFacade;
import com.banking.facade.OnboardingFacade.Method;

public class OTPConfigurationManager {
    private List<String> availableMethods = Arrays.asList("SMS", "Email");
    
    // Map to store user preferences (username -> list of methods)
    private Map<String, List<OnboardingFacade.Method>> userPreferences = new HashMap<>();

    public List<String> getAvailableMethods() {
        return availableMethods;
    }

    public boolean validateMethod(String method) {
        boolean isValid = availableMethods.contains(method);
        System.out.println("Validating OTP method: " + method + " -> " + isValid);
        return isValid;
    }
    
}
