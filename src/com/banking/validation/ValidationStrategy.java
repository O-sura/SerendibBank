package com.banking.validation;

public interface ValidationStrategy {
    boolean validate(String input);
    String getErrorMessage();
}
