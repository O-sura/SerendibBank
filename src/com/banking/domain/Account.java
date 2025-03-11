package com.banking.domain;

public class Account {
    private String accountNumber;
    private User accOwner;

    public Account(String accountNumber, String accType,User accOwner){
        this.accountNumber = accountNumber;
        this.accOwner = accOwner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public User getBankAccOwner() {
        return accOwner;
    }

}
