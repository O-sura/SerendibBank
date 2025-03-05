package com.banking.client;

import com.banking.domain.User;
import com.banking.facade.LoginFacade;
import com.banking.manager.OTPManager;
import com.banking.manager.SecurityManager;
import com.banking.manager.TwoFactorAuthManager;
import com.banking.manager.PasswordResetManager;
import com.banking.manager.UserProfileManager;
import com.banking.notification.BasicNotificationService;
import com.banking.notification.NotificationService;
import com.banking.service.AuthenticationService;
import com.banking.service.DashboardProvider;
import com.banking.util.DatabaseManager;
import com.banking.util.TokenGenerator;

public class TestLoginFlow {
    public static void main(String[] args) {
        // Create and initialize all dependencies
        AuthenticationService authService = new AuthenticationService();
        OTPManager otpManager = new OTPManager();
        NotificationService notificationService = new BasicNotificationService();
        TwoFactorAuthManager tfaManager = new TwoFactorAuthManager(otpManager, notificationService);
        SecurityManager securityManager = SecurityManager.getInstance();
        DashboardProvider dashboardProvider = new DashboardProvider();
        
        // Initialize password reset dependencies
        DatabaseManager dbManager = DatabaseManager.getInstance();
        UserProfileManager profileManager = new UserProfileManager();
        TokenGenerator tokenGenerator = new TokenGenerator();
        int tokenExpiryHours = 24;
        
        PasswordResetManager passwordResetManager = new PasswordResetManager(
            notificationService,
            profileManager,
            dbManager,
            tokenGenerator,
            tokenExpiryHours
        );
        
        // Create LoginFacade with all dependencies
        LoginFacade loginFacade = new LoginFacade();
        loginFacade.setAuthService(authService);
        loginFacade.setTfaManager(tfaManager);
        loginFacade.setSecurityManager(securityManager);
        loginFacade.setDashboardProvider(dashboardProvider);
        loginFacade.setPasswordResetManager(passwordResetManager);
        
        // Test normal login flow
        System.out.println("=== Testing Normal Login Flow ===");
        loginFacade.attemptLogin("testuser", "password123");
        System.out.println("=== Normal Login Flow Test Completed ===\n");
        
        // Test password reset flow
        System.out.println("=== Testing Password Reset Flow ===");
        loginFacade.initiatePasswordReset("testuser");
        loginFacade.validatePasswordResetToken("reset_token_123");
        loginFacade.resetPassword("reset_token_123", "newPassword456");
        System.out.println("=== Password Reset Flow Test Completed ===");
    }
}