package com.banking.manager;

public class SecurityManager {
    private static SecurityManager instance;

    private SecurityManager() {
        // Private constructor for singleton
    }

    public static SecurityManager getInstance() {
        if (instance == null) {
            instance = new SecurityManager();
        }
        return instance;
    }

    public void lockAccount(String username, int duration) {
        System.out.println("Account locked for user: " + username + " for " + duration + " hours.");
    }

    public boolean isAccountLocked(String username) {
        System.out.println("Checking if account is locked for user: " + username);
        return false; // Placeholder logic
    }

    public boolean enforcePasswordPolicy(String password) {
        System.out.println("Enforcing password policy for password: " + password);
        return password != null && password.length() >= 8; // Placeholder logic
    }
}
