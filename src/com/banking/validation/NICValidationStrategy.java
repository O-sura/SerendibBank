package com.banking.validation;

public class NICValidationStrategy implements ValidationStrategy {
    @Override
    public boolean validate(String nic) {
        // Placeholder logic for NIC validation
        return nic != null && nic.matches("\\d{9}[Vv]?"); // Example NIC validation
    }

    @Override
    public String getErrorMessage() {
        return "Invalid NIC format. NIC must be 9 digits followed by an optional 'V'.";
    }
}
