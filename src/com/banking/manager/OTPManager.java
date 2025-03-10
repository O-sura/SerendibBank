package com.banking.manager;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banking.domain.User;
import com.banking.facade.OnboardingFacade.Method;
import com.banking.notification.NotificationService;
import com.banking.notification.factory.ConcreteNotificationFactory;
import com.banking.notification.factory.NotificationFactory;
import com.banking.util.DatabaseManager;

public class OTPManager {
    private Map<String, String> otpStore = new HashMap<>();
    private Map<String, Integer> attemptCounter = new HashMap<>();
    private Map<String, Long> otpTimestamps = new HashMap<>();
    private NotificationService notificationService;
    private NotificationFactory notificationFactory = new ConcreteNotificationFactory();
    private static final int MAX_ATTEMPTS = 3;
    private static final long OTP_EXPIRY_MS = 30000;
    private static DatabaseManager dbManager = DatabaseManager.getInstance();

    public String generateOTP(String otpID) {
        String otp = "123456";
        dbManager.executeQuery("Storing OTP for user: " + otpID + "/ OTP: " + otp);
        
        //NOTE: These set of hashmaps are used just to simulate the OTP generation and storage
        otpStore.put(otpID, otp);
        otpTimestamps.put(otpID, System.currentTimeMillis());
        attemptCounter.put(otpID, 0);

        return otp;
    }

    public boolean validateOTP(String user, String otp) {
        if (isOTPExpired(user)) {
            System.out.println("OTP expired for user: " + user);
            return false;
        }

        if (!otpStore.containsKey(user) || !otpStore.get(user).equals(otp)) {
            int attempts = attemptCounter.getOrDefault(user, 0) + 1;
            attemptCounter.put(user, attempts);
            if (attempts >= MAX_ATTEMPTS) {
                System.out.println("User locked due to excessive OTP failures: " + user);
            }
            return false;
        }

        attemptCounter.put(user, 0);
        return true;
    }

    public void deliverOTP(String user, String otp, List<Method> methods) {
        // Store method types for decision making
        boolean hasSMS = false;
        boolean hasEmail = false;
        String smsCredential = null;
        String emailCredential = null;
        
        // Analyze provided methods
        for (Method method : methods) {
            if ("SMS".equals(method.type())) {
                hasSMS = true;
                smsCredential = method.credential();
            } else if ("Email".equals(method.type())) {
                hasEmail = true;
                emailCredential = method.credential();
            }
        }

        if (hasSMS && hasEmail) {
            System.out.println("Using combined notification service (SMS + Email)");
            notificationService = notificationFactory.createCombineNotification();
            // Send to both, starting with mobile (as per requirement)
            notificationService.send(smsCredential, "Your OTP is: " + otp);
            notificationService.send(emailCredential, "Your OTP is: " + otp);
        } else if (hasSMS) {
            System.out.println("Using mobile notification service");
            notificationService = notificationFactory.createMobileNotification();
            notificationService.send(smsCredential, "Your OTP is: " + otp);
        } else if (hasEmail) {
            System.out.println("Using email notification service");
            notificationService = notificationFactory.createEmailNotification();
            notificationService.send(emailCredential, "Your OTP is: " + otp);
        } else {
            // Fallback to default notification
            System.out.println("No recognized notification methods provided.");
            return;
        }

        System.out.println("OTP sent using " + 
            (notificationService != null ? notificationService.getDeliveryMethod() : "default") + 
            " delivery method");

    }

    public boolean isOTPExpired(String user) {
        System.out.println("Checking if OTP is expired.");
        return System.currentTimeMillis() - otpTimestamps.getOrDefault(user, 0L) > OTP_EXPIRY_MS;
    
    }

    public int getAttemptsLeft() {
        System.out.println("Getting OTP attempts left.");
        return 3; // Placeholder logic
    }
}
