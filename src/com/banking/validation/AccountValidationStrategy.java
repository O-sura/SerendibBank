package com.banking.validation;

public class AccountValidationStrategy implements ValidationStrategy {
    @Override
    public boolean validate(String accountNumber) {
        // Placeholder logic for Account Number validation
        return accountNumber != null && accountNumber.matches("\\d{10}"); // Example Account Number validation
    }

    @Override
    public String getErrorMessage() {
        return "Invalid Account Number format. Account Number must be 10 digits.";
    }
}
