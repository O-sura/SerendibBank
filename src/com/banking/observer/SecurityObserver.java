package com.banking.observer;

import com.banking.domain.User;
import com.banking.manager.SecurityManager;

public class SecurityObserver implements UserStatusObserver {
    private SecurityManager securityManager;

    public SecurityObserver(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    @Override
    public void update(User user, String oldStatus, String newStatus) {
        // Example: Lock the account if the status changes to "Suspended"
        if ("Suspended".equals(newStatus)) {
            securityManager.lockAccount(user.getUsername(), 24); // Lock for 24 hours
            System.out.println("Account locked for user: " + user.getUsername() + " due to status change to Suspended.");
        }

        // Log the status change
        System.out.println("Security alert: User " + user.getUsername() + " status changed from " + oldStatus + " to " + newStatus);
    }
}
