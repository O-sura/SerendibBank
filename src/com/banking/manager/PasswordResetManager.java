package com.banking.manager;

import java.security.NoSuchAlgorithmException;

import com.banking.facade.OnboardingFacade.Method;
import com.banking.notification.NotificationService;
import com.banking.notification.factory.ConcreteNotificationFactory;
import com.banking.notification.factory.NotificationFactory;
import com.banking.util.DatabaseManager;
import com.banking.util.TokenGenerator;

public class PasswordResetManager {
    private NotificationService notificationService;
    private UserProfileManager profileManager;
    private DatabaseManager dbManager;
    private TokenGenerator tokenGenerator;
    private NotificationFactory notificationFactory = new ConcreteNotificationFactory();
    private int tokenExpiryHours;

    public PasswordResetManager(NotificationService notificationService, UserProfileManager profileManager,
                                DatabaseManager dbManager, TokenGenerator tokenGenerator, int tokenExpiryHours) {
        this.notificationService = notificationService;
        this.profileManager = profileManager;
        this.dbManager = dbManager;
        this.tokenGenerator = tokenGenerator;
        this.tokenExpiryHours = tokenExpiryHours;
    }

    public void initiatePasswordReset(Method resetMethod) {
        System.out.println("Initiating password reset via: " + resetMethod.type() + " to: " + resetMethod.credential());
        
        String token = "";
        try {
            token = tokenGenerator.generateToken();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("Failed to generate token for password reset.");
            return;
        }
        
        String expiryDate = tokenGenerator.getTokenExpiryTimestamp(tokenExpiryHours);
        dbManager.executeQuery("Setting token for password reset, expires at: " + expiryDate);
        
        NotificationService service = null;
        
        // Select appropriate notification service based on method type
        switch (resetMethod.type()) {
            case "SMS":
                service = notificationFactory.createMobileNotification();
                break;
            case "Email":
                service = notificationFactory.createEmailNotification();
                break;
            case "Authenticator":
                service = notificationFactory.createAuthenticatorAppNotification();
                break;
            default:
                // Fall back to basic notification if type is unknown
                service = notificationService;
                System.out.println("Unknown reset method type, using default notification service.");
        }
        
        // Send the password reset token
        String recipient = resetMethod.credential();
        String message = "Your password reset token is: " + token + ". It expires at " + expiryDate;
        
        service.send(recipient, message);
        
        System.out.println("Token sent via " + 
            (service != null ? service.getDeliveryMethod() : "default method"));
    }

    public boolean validateResetToken(String token) {
        dbManager.executeQuery("Fetching password reset token for validation");
        boolean isValid = tokenGenerator.validateToken(token);
        System.out.println("Token validation result: " + isValid);
        return isValid;
    }

    public boolean completePasswordReset(String newPassword, String newPasswordRepeat) {
        if (newPassword.equals(newPasswordRepeat)) {
            profileManager.updatePassword("username", newPassword); // Placeholder username
            System.out.println("Password reset completed.");
            return true;
        }
        System.out.println("Password reset failed.");
        return false;
    }

    public void cancelPasswordReset(String username) {
        System.out.println("Password reset cancelled for user: " + username);
    }
}
