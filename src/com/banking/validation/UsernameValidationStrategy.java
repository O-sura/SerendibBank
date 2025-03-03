package com.banking.validation;

public class UsernameValidationStrategy implements ValidationStrategy {
    @Override
    public boolean validate(String username) {
        // Placeholder logic for Username validation
        return username != null && !username.isEmpty(); // Example Username validation
    }

    @Override
    public String getErrorMessage() {
        return "Username cannot be empty.";
    }
}
