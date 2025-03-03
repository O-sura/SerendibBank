package com.banking.registration;


import java.text.SimpleDateFormat;

import com.banking.domain.User;
import com.banking.util.AuditLogger;

public class LoginRedirector {
    private AuditLogger auditLogger;

    public void redirectToLogin(User user) {
        System.out.println("Redirecting user to login: " + user.getUsername());
        auditLogger = AuditLogger.getInstance();
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        String logMessage = user.getUsername() + "Logged In @" + timeStamp;
        auditLogger.log("USER_LOGIN", logMessage);
    }

    public void displayRedirectionMessage() {
        System.out.println("Displaying redirection message...");
    }
}
