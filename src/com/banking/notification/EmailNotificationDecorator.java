package com.banking.notification;

public class EmailNotificationDecorator extends NotificationDecorator {
    public EmailNotificationDecorator(NotificationService notification) {
        super(notification);
    }

    @Override
    public void send(String recipient, String message) {
        System.out.println("Sending email notification to " + recipient + ": " + message);
        super.send(recipient, message); // Chain to the next decorator
    }

    @Override
    public String getDeliveryMethod() {
        return "Email -> " + super.getDeliveryMethod();
    }
}
