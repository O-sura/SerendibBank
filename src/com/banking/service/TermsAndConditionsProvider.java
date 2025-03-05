package com.banking.service;

public class TermsAndConditionsProvider {
    private String termsUrl = "https://serandib.com/terms";;
    private String termsContent;

    public String getTermsAndConditions() {
        System.out.println("Fetching terms and conditions...");
        return termsContent;
    }

    public void setTermsUrl(String url) {
        System.out.println("Setting terms URL: " + url);
        this.termsUrl = url;
    }

    public void loadTermsFromUrl() {
        System.out.println("Loading terms from URL: " + termsUrl);
    }

    public boolean isLatestVersion() {
        System.out.println("Checking if terms are the latest version...");
        return true;
    }

    public void openTermsAndConditions() {
        System.out.println("Opening Terms & Conditions at: " + termsUrl);
    }
}
