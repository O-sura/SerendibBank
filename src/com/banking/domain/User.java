package com.banking.domain;

import java.util.Date;

public class User {
    private String username;
    private String password;
    private String displayName;
    private String NIC;
    private String PassportNo;
    private String mobileNumber;
    private String email;
    private String status;
    private boolean termsAccepted;
    private boolean isRegistered;
    private String preferredOtpMethod;
    private String passwordResetToken;
    private Date tokenExpiryDate;

    private User(UserBuilder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.displayName = builder.displayName;
        this.NIC = builder.NIC;
        this.PassportNo = builder.PassportNo;
        this.mobileNumber = builder.mobileNumber;
        this.email = builder.email;
        this.status = builder.status;
        this.termsAccepted = builder.termsAccepted;
        this.isRegistered = builder.isRegistered;
        this.preferredOtpMethod = builder.preferredOtpMethod;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDisplayName(String displayName){
        this.displayName = displayName;
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
        private String displayName;
        private String NIC;
        private String PassportNo;
        private String mobileNumber;
        private String email;
        private String status;
        private boolean termsAccepted;
        private boolean isRegistered;
        private String preferredOtpMethod;
    
        public UserBuilder withUsername(String username) {
            this.username = username;
            return this;
        }
    
        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }
    
        public UserBuilder withDisplayName(String displayName) {
            this.displayName = displayName;
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
    
        public UserBuilder withRegistered(boolean registered) {
            this.isRegistered = registered;
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