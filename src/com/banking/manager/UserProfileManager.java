package com.banking.manager;

import java.util.ArrayList;
import java.util.List;

import com.banking.domain.User;
import com.banking.observer.UserStatusObserver;

public class UserProfileManager {
    private List<UserStatusObserver> observers = new ArrayList<>();

    public void createProfile(User user) {
        System.out.println("Creating profile for user: " + user.getUsername());
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
        // Placeholder logic - in a real implementation, you would fetch the user from a database
        return new User.UserBuilder()
                .withNic(nicPassport)
                .withUsername("default_username")
                .withStatus("active")
                .build();
    }

    public User getUserByUsername(String username) {
        System.out.println("Fetching user by username: " + username);
        // Placeholder logic - in a real implementation, you would fetch the user from a database
        return new User.UserBuilder()
                .withUsername(username)
                .withStatus("active")
                .build();
    }

    public void updateOTPPreference(String username, String preferredMethod) {
        System.out.println("Updating OTP preference for user: " + username + " to " + preferredMethod);
    }

    public void updatePassword(String username, String newPassword) {
        System.out.println("Updating password for user: " + username);
    }
}
