package com.banking.notification.factory;

import com.banking.notification.NotificationService;

public interface NotificationFactory {
    NotificationService createMobileNotification();
    NotificationService createEmailNotification();
    NotificationService createCombineNotification();
    NotificationService createAuthenticatorAppNotification();
}
