package com.banking.notification;

public interface NotificationService {
    void send(String recipient, String message);
    String getDeliveryMethod();
}

