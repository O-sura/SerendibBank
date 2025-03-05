package com.banking.facade;


import com.banking.domain.User;
import com.banking.manager.UserProfileManager;
import com.banking.validation.ValidationService;
import com.banking.manager.OTPManager;
import com.banking.notification.NotificationService;
import com.banking.service.VerificationService;
import com.banking.service.LanguageSelector;
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
        selectPreferedLanguage("English");
        selectOnboardingType("Serendib Account");
        displayTermsAndConditions();
        acceptTermsAndConditions();
        validateIdentity("987654321V");
        validateAccount("ACC123456");
        checkExistingRegistration("987654321V", "ACC123456");
        sendOTP();
        verifyOTP("123456");
        selectVerificationMethod("Call Centre");
        createUserProfile("user123", "password123");
        completeOnboarding();
    }

    public void selectPreferedLanguage(String language) {
        System.out.println("Selecting language: " + language);
        LanguageSelector.selectLanguage(language);
    }

    public void selectOnboardingType(String type) {
        if (!type.equals("Serendib Account")) {
            throw new IllegalArgumentException("Invalid onboarding type");
        }
        System.out.println("Onboarding with type: " + type);
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
        otpManager.generateOTP("user123");
    }

    public void verifyOTP(String otp) {
        System.out.println("Verifying OTP: " + otp);
        otpManager.validateOTP("user123",otp);
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

    public void setValidationService(ValidationService validationService) {
        this.validationService = validationService;
    }

    public void setOtpManager(OTPManager otpManager) {
        this.otpManager = otpManager;
    }

    public void setProfileManager(UserProfileManager profileManager) {
        this.profileManager = profileManager;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void setVerificationService(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    public void setTermsProvider(TermsAndConditionsProvider termsProvider) {
        this.termsProvider = termsProvider;
    }

    public void setRegistrationChecker(RegistrationChecker registrationChecker) {
        this.registrationChecker = registrationChecker;
    }

    public void setLoginRedirector(LoginRedirector loginRedirector) {
        this.loginRedirector = loginRedirector;
    }

    public void setOtpConfigManager(OTPConfigurationManager otpConfigManager) {
        this.otpConfigManager = otpConfigManager;
    }
}
