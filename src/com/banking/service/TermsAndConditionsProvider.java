package com.banking.service;

public class TermsAndConditionsProvider {
    private String termsUrl = "https://serandib.com/terms";;
    private String termsContent = "Copyritght 2025 Serendib Bank. All rights reserved.";

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
        this.termsContent = "[Web]Copyritght 2025 Serendib Bank. All rights reserved.";
    }

    public boolean isLatestVersion() {
        System.out.println("Checking if terms are the latest version...");
        return true;
    }

}
