package com.banking.facade;


import java.util.List;

import com.banking.manager.UserProfileManager;
import com.banking.manager.UserSessionManager;
import com.banking.validation.ValidationService;
import com.banking.manager.OTPManager;
import com.banking.service.TermsAndConditionsProvider;
import com.banking.registration.RegistrationChecker;
import com.banking.registration.LoginRedirector;
import com.banking.manager.OTPConfigurationManager;

public class OnboardingFacade {
    private ValidationService validationService;
    private OTPManager otpManager;
    private UserProfileManager profileManager;
    private TermsAndConditionsProvider termsProvider;
    private RegistrationChecker registrationChecker;
    private LoginRedirector loginRedirector;
    private OTPConfigurationManager otpConfigManager;
    private UserSessionManager sessionManager = UserSessionManager.getInstance();

    public record Method(String type, String credential) {}

    public void startOnboarding() {

        //Need to check whether the user is already logged in(active session exists) and if so then redirect to dashboard
        if(sessionManager.isUserAuthenticated()){
            System.out.println("User already logged in.");
            redirectToLogin();
            return;
        }

        sessionManager.clearSession(); // Clear any existing session
        sessionManager.setOnboardingStage("initiated");


        System.out.println("Starting onboarding process...");
        selectPreferedLanguage("English"); //Language preference is hardcoded to English
        
        if(!validateIdentity("987654321V")){
            System.out.println("Identity validation failed. Exiting onboarding process.");
            return;
        }

        //Check whether the key NIC or Passport exist in the session data and if so get that and pass if to the checkExistingRegistration method
        if(checkExistingRegistration(sessionManager.getSessionData("NIC"))){
            System.out.println("User already exists. Redirecting to login page.");
            redirectToLogin();
            return;
        }

        try{
            selectOnboardingType("Serendib Account");
        }
        catch(IllegalArgumentException e){
            System.out.println("Invalid onboarding type. Exiting onboarding process.");
            return;
        }

        if(!validateAccount("ACC123456")){
            System.out.println("Account validation failed. Exiting onboarding process.");
            return;
        }

        displayTermsAndConditions();
        acceptTermsAndConditions();

        getAvailableOTPMethods();
        
        List<Method> methods = List.of(
            new Method("SMS", "+1234567890"),
            new Method("Email", "user@example.com")
        );
        sendOTP(methods);

        // OTP is hardcoded to 123456 for testing purposes
        if(verifyOTP("123456")){
            System.out.println("OTP verification successful.");
            sessionManager.setOnboardingStage("otp-verified");
        }
        else{
            System.out.println("OTP verification failed. Exiting onboarding process.");
            return;
        }

        selectVerificationMethod("Call Centre");
        if(!completeUserVerification()){// Just to mimic that the verification is completed
            System.out.println("User verification failed. Exiting onboarding process.");
            return;
        } 

        if(createUserProfile("user123", "password123")){
            System.out.println("User profile created successfully.");
        }
        else{
            System.out.println("User profile creation failed. Exiting onboarding process.");
            return;
        }

        completeOnboarding();
    }

    public void selectPreferedLanguage(String language) {
        System.out.println("Selecting language: " + language);
        sessionManager.setSessionData("language", language);
    }

    public void selectOnboardingType(String type) {
        // Define a list of types and check if the given type is in the list and if not throw IllegalArgumentException 
        List<String> types = List.of("Serendib Account", "Other");
        if (!types.contains(type)) {
            throw new IllegalArgumentException("Invalid onboarding type: " + type);
        }
        
        System.out.println("Onboarding with type: " + type);
        sessionManager.setSessionData("onboardingType", type);
    }

    public void displayTermsAndConditions() {
        System.out.println("Displaying terms and conditions...");
        String terms = termsProvider.getTermsAndConditions();
        System.out.println("\n\n" + terms);
    }

    public void acceptTermsAndConditions() {
        System.out.println("Terms and conditions accepted.");
        sessionManager.setSessionData("termsAccepted", "true");
    }

    public boolean validateIdentity(String nicPassport) {
        // type should be NIC if the lenght is 10, otherwise it should be Passport
        String type = nicPassport.length() >= 10 ? "NIC" : "Passport";
        System.out.println("Validating identity... " + nicPassport);
        String validationErr = validationService.validate(type, nicPassport);
        if (validationErr != null) {
            System.out.println("Validation failed: " + validationErr);
            return false;
        }
        sessionManager.setSessionData(type, nicPassport);
        return true;
    }

    public boolean validateAccount(String accountNumber) {
        System.out.println("Validating account: " + accountNumber);
        String validationErr = validationService.validate("ACCOUNT", accountNumber);
        if (validationErr != null) {
            System.out.println("Validation failed: " + validationErr);
            return false;
        }
        sessionManager.setSessionData("accountNo", accountNumber);
        return true;
    }

    public boolean checkExistingRegistration(String nicPassport) {
        // It is assumed that a user can't have more than one account associated with the same NIC/Passport
        System.out.println("Checking existing registration for NIC/Passport: " + nicPassport);
        boolean status = registrationChecker.checkUserExists(nicPassport);
        return status;
    }

    public void redirectToLogin() {
        loginRedirector.redirectTo("home/login");
    }

    public void sendOTP(List<Method> methods) {
        System.out.println("Sending OTP...");

        String otpID = sessionManager.getSessionData("accountNo");

        String otp = otpManager.generateOTP(otpID);
        otpManager.deliverOTP(otpID, otp, methods);
        sessionManager.setOnboardingStage("otp-sent");
    }

    public boolean verifyOTP(String otp) {
        System.out.println("Verifying OTP: " + otp);
        return otpManager.validateOTP(sessionManager.getSessionData("accountNo"),otp);
    }

    public void selectVerificationMethod(String method) {
        System.out.println("Selected verification method: " + method);
        sessionManager.setSessionData("verificationMethod", method);
        
    }

    public boolean completeUserVerification() {
        System.out.println("Completing verification via: " + sessionManager.getSessionData("verificationMethod"));
        sessionManager.setOnboardingStage("verified");
        return true;
    }

    //Get the list of available OTP methods
    public void getAvailableOTPMethods() {
        System.out.println("Available OTP methods: " + otpConfigManager.getAvailableMethods());
    }

    public boolean createUserProfile(String username, String password) {
        System.out.println("Trying to create user profile for username: " + username);
        return profileManager.createProfile(username, password);
    }

    public void completeOnboarding() {
        System.out.println("Onboarding completed.");
        sessionManager.clearSession();
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
