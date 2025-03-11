package com.banking.validation;

public class AccountValidationStrategy implements ValidationStrategy {
    @Override
    public boolean validate(String accountNumber) {
        // Placeholder logic for Account Number validation
        System.out.println("[+]Validating Account Number");
        //check account number is not null and starts with "ACC" and has total of 9 char length
        return accountNumber != null && accountNumber.matches("ACC\\d{6}"); // Example Account Number validation
    }

    @Override
    public String getErrorMessage() {
        return "Invalid Account Number format. Account Number must be starts with \"ACC\" and must have 9 digit length.";
    }
}
