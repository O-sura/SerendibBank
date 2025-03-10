package com.banking.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.banking.domain.User;
import com.banking.facade.OnboardingFacade;
import com.banking.facade.OnboardingFacade.Method;
import com.banking.observer.UserStatusObserver;
import com.banking.util.DatabaseManager;

public class UserProfileManager {
    private List<UserStatusObserver> observers = new ArrayList<>();
    private DatabaseManager dbManager = DatabaseManager.getInstance();
    private UserSessionManager sessionManager = UserSessionManager.getInstance();

    private boolean checkUserExistsWithUsername(String username) {
        return dbManager.executeQuery("SELECT * FROM users WHERE username = '" + username + "'") > 0;
    }

    private List<String> suggestUsernames(String username) {
        return Arrays.asList(username + "123", username + "456", username + "789");
    }

    public boolean createProfile(String username, String password) {
        if (checkUserExistsWithUsername(username)) {
            List<String> suggestions = suggestUsernames(username);
            System.out.println("Username taken. Suggested alternatives: " + suggestions);
            return false;
        } 
        
        //check whether the password contains one uppercase letter, one lowercase letter, one digit, and one special character
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            System.out.println("Password must contain at least 8 characters, one uppercase letter, one lowercase letter, one digit, and one special character");
            return false;
        }
        
        User newUser = new User.UserBuilder()
                .withNic(sessionManager.getSessionData("NIC"))
                .withPassportNo(sessionManager.getSessionData("Passport"))
                .withAccountNumber(sessionManager.getSessionData("accountNumber"))
                .witPrefferedLanguage(sessionManager.getSessionData("language"))
                .withEmail(password)
                .withMobileNumber(password)
                .withVerified(true)
                .withUsername(username)
                .withPassword(password)
                .withTermsAccepted(sessionManager.getSessionData("termsAccepted") == "true"? true : false)
                .withStatus("active")
                .build();

        dbManager.executeQuery("Adding new user " + newUser +" to the database");
        return true;
    }

    public void updateStatus(User user, String newStatus) {
        String oldStatus = user.getStatus();
        user.setStatus(newStatus);
        notifyObservers(user, oldStatus, newStatus);
        System.out.println("Updated status for user: " + user.getUsername() + " to " + newStatus);
    }

    public void registerObserver(UserStatusObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(UserStatusObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(User user, String oldStatus, String newStatus) {
        for (UserStatusObserver observer : observers) {
            observer.update(user, oldStatus, newStatus);
        }
    }

    public boolean checkUserExists(String nicPassport, String accountNumber) {
        System.out.println("Checking if user exists with NIC/Passport: " + nicPassport + " and account number: " + accountNumber);
        return true; // Placeholder logic
    }

    public User getUserByNicPassport(String nicPassport) {
        System.out.println("Fetching user by NIC/Passport: " + nicPassport);
        // Placeholder logic - in a real implementation, you would fetch the user from a database and return it
        dbManager.executeQuery("Fetching user data associated with the NIC/Passport" + nicPassport + "'");
        return new User.UserBuilder()
                .withNic(nicPassport)
                .withUsername("defaultUser")
                .withStatus("active")
                .build();
    }

    public User getUserByUsername(String username) {
        System.out.println("Fetching user by username: " + username);
        // Placeholder logic - in a real implementation, you would fetch the user from a database
        return new User.UserBuilder()
                .withUsername(username)
                .withEmail("user@example.com")
                .withMobileNumber("+1234567890")
                .withAccountNumber("ACC123456")
                .withVerified(true)
                .withStatus("active")
                .build();
    }

    public void updateOTPPreference(String username, String preferredMethod) {
        System.out.println("Updating OTP preference for user: " + username + " to " + preferredMethod);
    }

    public List<Method> getOTPPreference(User user) {
        System.out.println("Fetching OTP preference for user: " + user.getUsername());
        
        // Get user's preferred OTP methods from profile
        List<Method> otpMethods = new ArrayList<>();
        
        // If user has preferred SMS method
        String mobileNumber = user.getMobileNumber();
        if (mobileNumber != null && !mobileNumber.isEmpty()) {
            otpMethods.add(new OnboardingFacade.Method("SMS", mobileNumber));
        }
        
        // If user has preferred email method
        String email = user.getEmail();
        if (email != null && !email.isEmpty()) {
            otpMethods.add(new OnboardingFacade.Method("Email", email));
        }
        
        // If no methods found, use a default
        if (otpMethods.isEmpty()) {
            System.out.println("No OTP delivery methods found for user. Using default method.");
            otpMethods.add(new Method("SMS", user.getUsername()));
        }

        return otpMethods;
    }

    public void updatePassword(String username, String newPassword) {
        System.out.println("Updating password for user: " + username);
    }
}
