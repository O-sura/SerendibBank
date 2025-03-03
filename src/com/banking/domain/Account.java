package com.banking.domain;

public class Account {
    private String accountNumber;
    private String accType;
    private User accOwner;

    public Account(String accountNumber, String accType,User accOwner){
        this.accountNumber = accountNumber;
        this.accOwner = accOwner;
        this.accType = accType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public User getBankAccOwner() {
        return accOwner;
    }

}
