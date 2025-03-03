package com.banking.service;


import com.banking.util.DatabaseManager;

public class AuthenticationService {
    private DatabaseManager dbManager;

    public boolean validateCredentials(String username, String password) {
        System.out.println("Validating credentials for username: " + username);
        this.dbManager = DatabaseManager.getInstance();
        return dbManager.executeQuery("SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'") != 0;
    }

    public int getFailedAttempts(String username) {
        System.out.println("Fetching failed login attempts for username: " + username);
        return 0; // Placeholder
    }

    public void resetFailedAttempts(String username) {
        System.out.println("Resetting failed login attempts for username: " + username);
    }
}
