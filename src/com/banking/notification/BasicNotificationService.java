package com.banking.notification;

// Kind of useless class
public class BasicNotificationService implements NotificationService {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Sending basic notification to " + recipient + ": " + message);
    }

    @Override
    public String getDeliveryMethod() {
        return "Basic";
    }
}


