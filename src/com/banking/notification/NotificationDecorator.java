package com.banking.notification;


public abstract class NotificationDecorator implements NotificationService {
    protected NotificationService wrappedNotification;

    public NotificationDecorator(NotificationService notification) {
        this.wrappedNotification = notification;
    }

    @Override
    public void send(String recipient, String message) {
        if (wrappedNotification != null) {
            wrappedNotification.send(recipient, message);
        }
    }

    @Override
    public String getDeliveryMethod() {
        return wrappedNotification != null ? wrappedNotification.getDeliveryMethod() : "None";
    }
}

