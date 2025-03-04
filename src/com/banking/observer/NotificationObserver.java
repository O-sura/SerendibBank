package com.banking.observer;

import com.banking.domain.User;
import com.banking.notification.NotificationService;

public class NotificationObserver implements UserStatusObserver {
    private NotificationService notificationService;

    public NotificationObserver(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void update(User user, String oldStatus, String newStatus) {
        // Send a notification about the status change
        String message = "User " + user.getUsername() + " status changed from " + oldStatus + " to " + newStatus;
        notificationService.send(user.getEmail(), message);
        System.out.println("Notification sent: " + message);
    }
}
