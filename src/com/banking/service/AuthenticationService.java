package com.banking.service;


import com.banking.util.DatabaseManager;

public class AuthenticationService {
    private DatabaseManager dbManager = DatabaseManager.getInstance();

    public boolean validateCredentials(String username, String password) {
        System.out.println("Validating credentials for username: " + username);
        dbManager.executeQuery("Fetching user " +  username +"\'s data from DB");
        return true; // Placeholder
    }

    public int getFailedAttempts(String username) {
        System.out.println("Fetching failed login attempts for username: " + username);
        return 0; // Placeholder
    }

    public void updateFailedAttempts(String username) {
        System.out.println("Updating failed login attempts for username: " + username);
    }
    
    public void resetFailedAttempts(String username) {
        System.out.println("Resetting failed login attempts for username: " + username);
    }
}
