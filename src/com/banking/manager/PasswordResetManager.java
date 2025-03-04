package com.banking.manager;

import java.security.NoSuchAlgorithmException;

import com.banking.notification.NotificationService;
import com.banking.util.DatabaseManager;
import com.banking.util.TokenGenerator;

public class PasswordResetManager {
    private NotificationService notificationService;
    private UserProfileManager profileManager;
    private DatabaseManager dbManager;
    private TokenGenerator tokenGenerator;
    private int tokenExpiryHours;

    public PasswordResetManager(NotificationService notificationService, UserProfileManager profileManager,
                                DatabaseManager dbManager, TokenGenerator tokenGenerator, int tokenExpiryHours) {
        this.notificationService = notificationService;
        this.profileManager = profileManager;
        this.dbManager = dbManager;
        this.tokenGenerator = tokenGenerator;
        this.tokenExpiryHours = tokenExpiryHours;
    }

    public void initiatePasswordReset(String username) {
        String token = "";
        try {
            token = tokenGenerator.generateToken();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return; // Exit the method if token generation fails
        }
        String expiryDate = tokenGenerator.getTokenExpiryTimestamp(tokenExpiryHours);
        dbManager.executeQuery("Setting token for password reset");
        notificationService.send(username, "Your password reset token is: " + token);
        System.out.println("Password reset initiated for user: " + username);
    }

    public boolean validateResetToken(String token) {
        dbManager.executeQuery("Fetching password reset token for validation");
        boolean isValid = tokenGenerator.validateToken(token);
        System.out.println("Token validation result: " + isValid);
        return isValid;
    }

    public boolean completePasswordReset(String token, String newPassword) {
        if (validateResetToken(token)) {
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
