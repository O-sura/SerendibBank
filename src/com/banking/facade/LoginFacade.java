package com.banking.facade;


import com.banking.service.AuthenticationService;
import com.banking.manager.TwoFactorAuthManager;
import com.banking.manager.UserProfileManager;
import com.banking.manager.UserSessionManager;
import com.banking.registration.LoginRedirector;
import com.banking.manager.SecurityManager;
import com.banking.service.DashboardProvider;
import com.banking.util.AuditLogger;

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
    private AuditLogger auditLogger = AuditLogger.getInstance();

    public void attemptLogin(String username, String password) {
        auditLogger.log("LOGIN_ATTEMPT", "Login attempt for username: " + username);
        System.out.println("Attempting login for username: " + username);
        
        if (isAccountLocked(username)) {
            auditLogger.log("LOGIN_FAILED", "Login attempt failed - Account locked for user: " + username);
            System.out.println("Account is locked. Please contact us via support@serandib-bank.lk");
            return;
        }

        if (!authService.validateCredentials(username, password)){
            int failedAttempts = authService.getFailedAttempts(username);
            authService.updateFailedAttempts(username);
            auditLogger.log("LOGIN_FAILED", "Invalid credentials for user: " + username + ", failed attempts: " + (failedAttempts + 1));
            if (failedAttempts >= 3) {
                securityManager.lockAccount(username,3);
                auditLogger.log("ACCOUNT_LOCKED", "Account locked due to multiple failed login attempts for user: " + username);
                System.out.println("Account locked due to multiple failed login attempts.");
            }
            return;
        }else{
            auditLogger.log("CREDENTIALS_VALID", "Valid credentials for user: " + username);
            sessionManager.setCurrentUser(userProfileManager.getUserByUsername(username));
            auditLogger.log("USER_SESSION", "User session created for: " + username);
        }

        
        User currUser = sessionManager.getCurrentUser();
        //initialize two factor authentication
        auditLogger.log("2FA_INITIATED", "Two-factor authentication initiated for user: " + currUser.getUsername());
        initiateTwoFactorAuth(currUser);

        if(!verifyOTP(currUser, "123456")) {
            auditLogger.log("2FA_FAILED", "Two-factor authentication failed for user: " + currUser.getUsername());
            System.out.println("Two-factor authentication failed.");
            return;
        }
        auditLogger.log("LOGIN_SUCCESS", "User " + currUser.getUsername() + " successfully logged in");
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
        auditLogger.log("PASSWORD_RESET", "Password reset initiated via " + resetMethod.type() + " to: " + resetMethod.credential());
        passwordResetManager.initiatePasswordReset(resetMethod);
        loginRedirector.redirectTo("home/forgot_password/verify_otp");
    }

    public boolean validatePasswordResetToken(String token) {
        System.out.println("Validating password reset token: " + token);
        boolean isValid = passwordResetManager.validateResetToken(token);
        auditLogger.log("TOKEN_VALIDATION", "Password reset token validation: " + (isValid ? "Success" : "Failed"));
        return isValid;
    }

    public void resetPassword(String newPassword, String newPasswordConfirm) {
        boolean success = passwordResetManager.completePasswordReset(newPassword, newPasswordConfirm);
        auditLogger.log("PASSWORD_CHANGED", "Password reset completed: " + (success ? "Success" : "Failed"));
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

    public void setUserProfileManager(UserProfileManager userProfileManager) {
        this.userProfileManager = userProfileManager;
    }

    public void setLoginRedirector(LoginRedirector loginRedirector) {
        this.loginRedirector = loginRedirector;
    }
}
