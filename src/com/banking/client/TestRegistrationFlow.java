package com.banking.client;

import com.banking.facade.OnboardingFacade;
import com.banking.manager.OTPConfigurationManager;
import com.banking.manager.OTPManager;
import com.banking.manager.UserProfileManager;
import com.banking.registration.LoginRedirector;
import com.banking.registration.RegistrationChecker;
import com.banking.service.TermsAndConditionsProvider;
import com.banking.util.DatabaseManager;
import com.banking.validation.AccountValidationStrategy;
import com.banking.validation.NICValidationStrategy;
import com.banking.validation.ValidationService;

public class TestRegistrationFlow {
    public static void main(String[] args) {
        // Create and initialize all dependencies
        ValidationService validationService = new ValidationService();
        validationService.registerStrategy("NIC", new NICValidationStrategy());
        validationService.registerStrategy("ACCOUNT", new AccountValidationStrategy());
        
        OTPManager otpManager = new OTPManager();
        UserProfileManager profileManager = new UserProfileManager();
        TermsAndConditionsProvider termsProvider = new TermsAndConditionsProvider();
        
        // Initialize registration checker with its dependencies
        DatabaseManager dbManager = DatabaseManager.getInstance();
        RegistrationChecker registrationChecker = new RegistrationChecker();
        registrationChecker.setDbManager(dbManager);
        registrationChecker.setProfileManager(profileManager);
        
        LoginRedirector loginRedirector = new LoginRedirector();
        OTPConfigurationManager otpConfigManager = new OTPConfigurationManager();
        
        // Create OnboardingFacade with all dependencies
        OnboardingFacade onboardingFacade = new OnboardingFacade();
        onboardingFacade.setValidationService(validationService);
        onboardingFacade.setOtpManager(otpManager);
        onboardingFacade.setProfileManager(profileManager);

        onboardingFacade.setTermsProvider(termsProvider);
        onboardingFacade.setRegistrationChecker(registrationChecker);
        onboardingFacade.setLoginRedirector(loginRedirector);
        onboardingFacade.setOtpConfigManager(otpConfigManager);
        
        // Start the onboarding process
        System.out.println("=== Starting Onboarding Flow Test ===");
        onboardingFacade.startOnboarding();
        System.out.println("=== Onboarding Flow Test Completed ===");
    }
}

