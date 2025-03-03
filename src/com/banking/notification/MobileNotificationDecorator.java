package com.banking.notification;

public class MobileNotificationDecorator extends NotificationDecorator {
    public MobileNotificationDecorator(NotificationService notification) {
        super(notification);
    }

    @Override
    public void send(String recipient, String message) {
        System.out.println("Sending mobile notification to " + recipient + ": " + message);
        super.send(recipient, message); // Chain to the next decorator
    }

    @Override
    public String getDeliveryMethod() {
        return "Mobile -> " + super.getDeliveryMethod();
    }
}
