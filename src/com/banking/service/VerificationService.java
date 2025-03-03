package com.banking.service;


import com.banking.domain.User;

public class VerificationService {
    public void initiateCallCenterVerification(User user) {
        System.out.println("Initiating call center verification for user: " + user.getUsername());
    }

    public void initiateBranchVerification(User user) {
        System.out.println("Initiating branch verification for user: " + user.getUsername());
    }

    public void completeVerification(User user) {
        System.out.println("Completing verification for user: " + user.getUsername());
    }
}