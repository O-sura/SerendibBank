package com.banking.notification;

public class AuthenticatorAppDecorator extends NotificationDecorator {
    public AuthenticatorAppDecorator(NotificationService notification) {
        super(notification);
    }

    @Override
    public void send(String recipient, String message) {
        System.out.println("Sending authenticator app notification to " + recipient + ": " + message);
        super.send(recipient, message);
    }

    @Override
    public String getDeliveryMethod() {
        return "Authenticator App -> " + super.getDeliveryMethod();
    }
}
