package com.banking.domain;

import java.util.Date;

public class User {
    private String username;
    private String password;
    private String NIC;
    private String PassportNo;
    private String prefferedLanguage;
    private String accountNumber;
    private String mobileNumber;
    private String email;
    private String status;
    private boolean termsAccepted;
    private boolean isRegistered;
    private boolean isVerified;
    private boolean isLocked = false;
    private String preferredOtpMethod;
    private String passwordResetToken;
    private Date tokenExpiryDate;

    private User(UserBuilder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.NIC = builder.NIC;
        this.PassportNo = builder.PassportNo;
        this.accountNumber = builder.accountNumber;
        this.mobileNumber = builder.mobileNumber;
        this.email = builder.email;
        this.status = builder.status;
        this.termsAccepted = builder.termsAccepted;
        this.isVerified = builder.isVerified;
        this.prefferedLanguage = builder.prefferedLanguage;
        this.preferredOtpMethod = builder.preferredOtpMethod;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPrefferedLanguage() {
        return prefferedLanguage;
    }

    public void setPrefferedLanguage(String prefferedLanguage) {
        this.prefferedLanguage = prefferedLanguage;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        System.out.println("Setting user lock status to: " + locked);
        this.isLocked = locked;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    //getters and setters for password
    public String getPassword(){
        return password;
    }

    public void setNicNo(String NICNo){
        this.NIC = NICNo;
    }

    public void setPassportNo(String PassportNo){
        this.PassportNo = PassportNo;
    }

    public void setMobileNumber(String mobileNumber){
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber(){
        return mobileNumber;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        System.out.println("Setting user status to: " + status);
        this.status = status;
    }

    public boolean hasAcceptedTerms() {
        return termsAccepted;
    }

    public void acceptTerms() {
        System.out.println("User has accepted terms and conditions");
        this.termsAccepted = true;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        System.out.println("Setting user registration status to: " + registered);
        this.isRegistered = registered;
    }

    public boolean getVerificationStatus() {
        return isVerified;
    }

    public void setVerificationStatus(boolean status) {
        this.isVerified = status;
    }

    public String getPreferredOtpMethod() {
        return preferredOtpMethod;
    }

    public void setPreferredOtpMethod(String method) {
        System.out.println("Setting preferred OTP method to: " + method);
        this.preferredOtpMethod = method;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPasswordResetToken() {
        return passwordResetToken;

    }

    public void setPasswordResetToken(String token) {
        System.out.println("Setting password reset token");
        this.passwordResetToken = token;
    }

    public boolean isTokenValid() {
        if (passwordResetToken == null || tokenExpiryDate == null) {
            return false;
        }
        return tokenExpiryDate.after(new Date());
    }


    public static class UserBuilder {
        private String username;
        private String password;
        private String NIC;
        private String PassportNo;
        private String mobileNumber;
        private String accountNumber;
        private String email;
        private String status;
        private boolean termsAccepted;
        private boolean isVerified;
        private String prefferedLanguage;
        private String preferredOtpMethod;
    
        public UserBuilder withUsername(String username) {
            this.username = username;
            return this;
        }
    
        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }
    
        public UserBuilder withNic(String NICNo) {
            this.NIC = NICNo;
            return this;
        }
    
        public UserBuilder withPassportNo(String PassportNo) {
            this.PassportNo = PassportNo;
            return this;
        }

        public UserBuilder withAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public UserBuilder witPrefferedLanguage(String prefferedLanguage) {
            this.prefferedLanguage = prefferedLanguage;
            return this;
        }
    
        public UserBuilder withMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;
        }
    
        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }
    
        public UserBuilder withStatus(String status) {
            this.status = status;
            return this;
        }
    
        public UserBuilder withTermsAccepted(boolean accepted) {
            this.termsAccepted = accepted;
            return this;
        }
    
        public UserBuilder withVerified(boolean verified) {
            this.isVerified = verified;
            return this;
        }
    
        public UserBuilder withPreferredOtpMethod(String method) {
            this.preferredOtpMethod = method;
            return this;
        }
    
        public User build() {
            return new User(this);
        }
    }
}