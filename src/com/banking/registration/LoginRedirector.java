package com.banking.registration;



import com.banking.domain.User;
import com.banking.util.AuditLogger;

public class LoginRedirector {

    public void redirectTo(String redirectUrl) {
        System.out.println("Redirecting user to: " + redirectUrl);
    }

    public void displayRedirectionMessage() {
        System.out.println("Displaying redirection message...");
    }
}
