package com.banking.service;


import com.banking.domain.User;

public class DashboardProvider {
    public void loadDashboard(User user) {
        System.out.println("Loading dashboard for user: " + user.getUsername());
    }

    public void getAccountBalances(User user) {
        System.out.println("Fetching account balances for user: " + user.getUsername());
    }

    public void getLoanSummary(User user) {
        System.out.println("Fetching loan summary for user: " + user.getUsername());
    }

    public void getCreditCardSummary(User user) {
        System.out.println("Fetching credit card summary for user: " + user.getUsername());
    }
}
