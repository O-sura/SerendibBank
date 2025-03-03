package com.banking.validation;

public class PasswordValidationStrategy implements ValidationStrategy {
    @Override
    public boolean validate(String password) {
        // Placeholder logic for Password validation
        return password != null && password.length() >= 8; // Example Password validation
    }

    @Override
    public String getErrorMessage() {
        return "Password must be at least 8 characters long.";
    }
}
