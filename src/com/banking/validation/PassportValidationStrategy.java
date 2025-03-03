package com.banking.validation;

public class PassportValidationStrategy implements ValidationStrategy {
    @Override
    public boolean validate(String passport) {
        // Placeholder logic for Passport validation
        return passport != null && passport.matches("[A-Za-z]{2}\\d{7}"); // Example Passport validation
    }

    @Override
    public String getErrorMessage() {
        return "Invalid Passport format. Passport must be 2 letters followed by 7 digits.";
    }
}
