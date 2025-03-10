package com.banking.client;

import com.banking.domain.User;
import com.banking.facade.LoginFacade;
import com.banking.facade.OnboardingFacade.Method;
import com.banking.manager.OTPManager;
import com.banking.manager.SecurityManager;
import com.banking.manager.TwoFactorAuthManager;
import com.banking.manager.PasswordResetManager;
import com.banking.manager.UserProfileManager;
import com.banking.notification.BasicNotificationService;
import com.banking.notification.NotificationService;
import com.banking.registration.LoginRedirector;
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
        DatabaseManager dbManager = DatabaseManager.getInstance();
        
 
        UserProfileManager profileManager = new UserProfileManager();
        TwoFactorAuthManager tfaManager = new TwoFactorAuthManager(otpManager, notificationService, profileManager);
        
        SecurityManager securityManager = SecurityManager.getInstance();
        DashboardProvider dashboardProvider = new DashboardProvider();
        LoginRedirector loginRedirector = new LoginRedirector();
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
        loginFacade.setUserProfileManager(profileManager);
        loginFacade.setPasswordResetManager(passwordResetManager);
        loginFacade.setLoginRedirector(loginRedirector);
        
        // Test normal login flow
        System.out.println("=== Testing Normal Login Start ===");
        loginFacade.attemptLogin("testuser", "password123");
        System.out.println("=== Normal Login Flow Test End ===\n");
        
        // Test password reset flow
        System.out.println("=== Testing Password Reset Start ===");
        Method resetMethod = new Method("Email", "testuser@gmail.com");
        loginFacade.initiatePasswordReset(resetMethod);
        loginFacade.validatePasswordResetToken("reset_token@123");
        loginFacade.resetPassword("newPassword456", "newPassword456");
        System.out.println("=== Password Reset Flow Test End ===");
    }
}