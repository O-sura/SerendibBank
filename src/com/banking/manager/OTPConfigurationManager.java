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
    
    // // Original method for backward compatibility
    // public void setUserPreference(User user, String preferredMethod) {
    //     System.out.println("Set OTP preference for user: " + user.getUsername() + " to " + preferredMethod);
    //     user.setPreferredOtpMethod(preferredMethod);
        
    //     // Also store in the map as a list with a single method
    //     List<OnboardingFacade.Method> methods = List.of(
    //         new OnboardingFacade.Method("default", preferredMethod)
    //     );
    //     userPreferences.put(user.getUsername(), methods);
    // }
    
    // // New method to handle multiple preferences
    // public void setUserPreferences(User user, List<OnboardingFacade.Method> preferredMethods) {
    //     System.out.println("Set multiple OTP preferences for user: " + user.getUsername());
        
    //     // Store all methods in the preferences map
    //     userPreferences.put(user.getUsername(), new ArrayList<>(preferredMethods));
        
    //     // Also set the primary method for backward compatibility
    //     if (!preferredMethods.isEmpty()) {
    //         String primaryMethod = preferredMethods.get(0).credential();
    //         user.setPreferredOtpMethod(primaryMethod);
    //         System.out.println("Primary OTP method set to: " + primaryMethod);
    //     }
        
    //     // Log all configured methods
    //     for (OnboardingFacade.Method method : preferredMethods) {
    //         System.out.println("- Added method: " + method.type() + " with credential: " + method.credential());
    //     }
    // }
    
    // // Method to get all user preferences
    // public List<OnboardingFacade.Method> getUserPreferences(User user) {
    //     return userPreferences.getOrDefault(user.getUsername(), Collections.emptyList());
    // }

    // // Original method for backward compatibility
    // public String getUserPreference(User user) {
    //     System.out.println("Fetching primary OTP preference for user: " + user.getUsername());
    //     return user.getPreferredOtpMethod();
    // }
}
