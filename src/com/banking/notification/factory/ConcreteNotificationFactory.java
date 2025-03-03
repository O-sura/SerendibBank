package com.banking.notification.factory;

import com.banking.notification.AuthenticatorAppDecorator;
import com.banking.notification.EmailNotificationDecorator;
import com.banking.notification.MobileNotificationDecorator;
import com.banking.notification.NotificationService;

public class ConcreteNotificationFactory implements NotificationFactory {
    @Override
    public NotificationService createMobileNotification() {
        return new MobileNotificationDecorator(null);
    }

    @Override
    public NotificationService createEmailNotification() {
        return new EmailNotificationDecorator(null);
    }

    @Override
    public NotificationService createCombineNotification() {
        // Chain Mobile and Email notifications
        return new MobileNotificationDecorator(new EmailNotificationDecorator(null));
    }

    @Override
    public NotificationService createAuthenticatorAppNotification() {
        return new AuthenticatorAppDecorator(null);
    }
}