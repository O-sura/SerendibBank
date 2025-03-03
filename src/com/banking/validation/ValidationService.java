package com.banking.validation;


import java.util.HashMap;
import java.util.Map;

public class ValidationService {
    private Map<String, ValidationStrategy> strategies = new HashMap<>();

    public void registerStrategy(String type, ValidationStrategy strategy) {
        System.out.println("Registering validation strategy for type: " + type);
        strategies.put(type, strategy);
    }

    public String validate(String type, String input) {
        ValidationStrategy strategy = strategies.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException("No validation strategy registered for type: " + type);
        }

        if (!strategy.validate(input)) {
            return strategy.getErrorMessage();
        }
        return null; // No error message means validation passed
    }
}
