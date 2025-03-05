package com.banking.facade;


import com.banking.service.AuthenticationService;
import com.banking.manager.TwoFactorAuthManager;
import com.banking.manager.SecurityManager;
import com.banking.service.DashboardProvider;
import com.banking.manager.PasswordResetManager;

public class LoginFacade {
    private AuthenticationService authService;
    private TwoFactorAuthManager tfaManager;
    private SecurityManager securityManager;
    private DashboardProvider dashboardProvider;
    private PasswordResetManager passwordResetManager;

    public void attemptLogin(String username, String password) {
        System.out.println("Attempting login for username: " + username);
        sendOTP();
        verifyOTP("123456");
        completeTwoFactorAuth();
        loadDashboard();
    }

    public void sendOTP() {
        System.out.println("Sending OTP...");
    }

    public void verifyOTP(String otp) {
        System.out.println("Verifying OTP: " + otp);
    }

    public void completeTwoFactorAuth() {
        System.out.println("Two-factor authentication completed.");
    }

    public void loadDashboard() {
        System.out.println("Loading dashboard...");
    }

    public void initiatePasswordReset(String username) {
        System.out.println("Initiating password reset for username: " + username);
    }

    public void validatePasswordResetToken(String token) {
        System.out.println("Validating password reset token: " + token);
    }

    public void resetPassword(String token, String newPassword) {
        System.out.println("Resetting password with token: " + token);
    }

    // Setter methods for dependency injection
    public void setAuthService(AuthenticationService authService) {
        this.authService = authService;
    }
    
    public void setTfaManager(TwoFactorAuthManager tfaManager) {
        this.tfaManager = tfaManager;
    }
    
    public void setSecurityManager(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }
    
    public void setDashboardProvider(DashboardProvider dashboardProvider) {
        this.dashboardProvider = dashboardProvider;
    }
    
    public void setPasswordResetManager(PasswordResetManager passwordResetManager) {
        this.passwordResetManager = passwordResetManager;
    }
}
