package com.banking.facade;


import com.banking.domain.User;
import com.banking.manager.UserProfileManager;
import com.banking.validation.ValidationService;
import com.banking.manager.OTPManager;
import com.banking.notification.NotificationService;
import com.banking.service.VerificationService;
import com.banking.service.TermsAndConditionsProvider;
import com.banking.registration.RegistrationChecker;
import com.banking.registration.LoginRedirector;
import com.banking.manager.OTPConfigurationManager;

public class OnboardingFacade {
    private ValidationService validationService;
    private OTPManager otpManager;
    private UserProfileManager profileManager;
    private NotificationService notificationService;
    private VerificationService verificationService;
    private TermsAndConditionsProvider termsProvider;
    private RegistrationChecker registrationChecker;
    private LoginRedirector loginRedirector;
    private OTPConfigurationManager otpConfigManager;

    public void startOnboarding() {
        System.out.println("Starting onboarding process...");
        displayTermsAndConditions();
        acceptTermsAndConditions();
        validateIdentity("123456789");
        validateAccount("ACC123456");
        checkExistingRegistration("123456789", "ACC123456");
        sendOTP();
        verifyOTP("123456");
        createUserProfile("user123", "password123");
        completeOnboarding();
    }

    public void displayTermsAndConditions() {
        System.out.println("Displaying terms and conditions...");
        termsProvider.getTermsAndConditions();
    }

    public void acceptTermsAndConditions() {
        System.out.println("Terms and conditions accepted.");
        termsProvider.setTermsUrl("https://example.com/terms");
    }

    public void validateIdentity(String nicPassport) {
        System.out.println("Validating identity with NIC/Passport: " + nicPassport);
        validationService.validate("NIC", nicPassport);
    }

    public void validateAccount(String accountNumber) {
        System.out.println("Validating account: " + accountNumber);
        validationService.validate("ACCOUNT", accountNumber);
    }

    public void checkExistingRegistration(String nicPassport, String accountNumber) {
        System.out.println("Checking existing registration for NIC/Passport: " + nicPassport + " and Account: " + accountNumber);
        registrationChecker.checkUserExists(nicPassport, accountNumber);
    }

    public void redirectToLogin() {
        System.out.println("Redirecting to login...");
        User user = new User.UserBuilder()
                .withUsername("defaultUser")
                .withStatus("active")
                .build();
        loginRedirector.redirectToLogin(user);
    }

    public void sendOTP() {
        System.out.println("Sending OTP...");
        otpManager.generateOTP();
    }

    public void verifyOTP(String otp) {
        System.out.println("Verifying OTP: " + otp);
        otpManager.validateOTP(otp);
    }

    public void selectVerificationMethod(String method) {
        System.out.println("Selected verification method: " + method);
        User user = new User.UserBuilder()
                .withUsername("defaultUser")
                .withPreferredOtpMethod(method)
                .build();
        otpConfigManager.setUserPreference(user, method);
    }

    public void configureOTPPreference(String preferredMethod) {
        System.out.println("Configuring OTP preference: " + preferredMethod);
        User user = new User.UserBuilder()
                .withUsername("defaultUser")
                .withPreferredOtpMethod(preferredMethod)
                .build();
        otpConfigManager.setUserPreference(user, preferredMethod);
    }

    public void createUserProfile(String username, String password) {
        System.out.println("Creating user profile for username: " + username);
        User user = new User.UserBuilder()
                .withUsername(username)
                .withPassword(password)
                .withStatus("active")
                .build();
        profileManager.createProfile(user);
    }

    public void completeOnboarding() {
        System.out.println("Onboarding completed.");
        redirectToLogin();
    }
}
