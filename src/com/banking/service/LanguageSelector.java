package com.banking.service;

// Language Selection
public class LanguageSelector {
    private static String selectedLanguage = "English";

    public static void selectLanguage(String language) {
        selectedLanguage = language;
        System.out.println("Language set to: " + selectedLanguage);
    }

    public static String getSelectedLanguage() {
        return selectedLanguage;
    }
}
