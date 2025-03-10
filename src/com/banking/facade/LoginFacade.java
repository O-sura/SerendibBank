package com.banking.facade;


import com.banking.service.AuthenticationService;
import com.banking.manager.TwoFactorAuthManager;
import com.banking.manager.UserProfileManager;
import com.banking.manager.UserSessionManager;
import com.banking.registration.LoginRedirector;
import com.banking.manager.SecurityManager;
import com.banking.service.DashboardProvider;

import java.util.ArrayList;
import java.util.List;

import com.banking.domain.User;
import com.banking.facade.OnboardingFacade.Method;
import com.banking.manager.OTPManager;
import com.banking.manager.PasswordResetManager;

public class LoginFacade {
    private AuthenticationService authService;
    private UserProfileManager userProfileManager;
    private TwoFactorAuthManager tfaManager;
    private SecurityManager securityManager;
    private OTPManager otpManager;
    private DashboardProvider dashboardProvider;
    private LoginRedirector loginRedirector;
    private PasswordResetManager passwordResetManager;
    private UserSessionManager sessionManager = UserSessionManager.getInstance();

    public void attemptLogin(String username, String password) {
        System.out.println("Attempting login for username: " + username);
        
        if (isAccountLocked(username)) {
            System.out.println("Account is locked. Please contact us via support@serandib-bank.lk");
            return;
        }

        if (!authService.validateCredentials(username, password)){
            int failedAttempts = authService.getFailedAttempts(username);
            authService.updateFailedAttempts(username);
            if (failedAttempts >= 3) {
                securityManager.lockAccount(username,3);
                System.out.println("Account locked due to multiple failed login attempts.");
            }
            return;
        }else{
            sessionManager.setCurrentUser(userProfileManager.getUserByUsername(username));
        }

        
        User currUser = sessionManager.getCurrentUser();
        //initialize two factor authentication
        initiateTwoFactorAuth(currUser);

        if(!verifyOTP(currUser, "123456")) {
            System.out.println("Two-factor authentication failed.");
            return;
        }
        completeTwoFactorAuth();

        loadDashboard();
    }

    public boolean isAccountLocked(String username) {
        return securityManager.isAccountLocked(username);
    }

    public void initiateTwoFactorAuth(User user) {
        tfaManager.initiateOTPVerification(user);
    }

    public boolean verifyOTP(User user, String otp) {
        return tfaManager.verifyOTP(user, otp);
    }

    public void completeTwoFactorAuth() {
        System.out.println("Two-factor authentication completed.");
        sessionManager.setUserAuthenticated(true);
    }

    public void loadDashboard() {
        System.out.println("Loading dashboard...");
    }

    public void initiatePasswordReset(Method resetMethod) {
        System.out.println("Initiating password reset...");
        passwordResetManager.initiatePasswordReset(resetMethod);
        loginRedirector.redirectTo("home/forgot_password/verify_otp");
    }

    public boolean validatePasswordResetToken(String token) {
        System.out.println("Validating password reset token: " + token);
        return passwordResetManager.validateResetToken(token);
    }

    public void resetPassword(String newPassword, String newPasswordConfirm) {
        passwordResetManager.completePasswordReset(newPassword, newPasswordConfirm);
        loginRedirector.redirectTo("home/login");
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
