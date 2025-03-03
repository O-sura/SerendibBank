package com.banking.registration;

import com.banking.domain.User;
import com.banking.util.DatabaseManager;
import com.banking.manager.UserProfileManager;

public class RegistrationChecker {
    private DatabaseManager dbManager;
    private UserProfileManager profileManager;

    public boolean checkUserExists(String nicPassport, String accountNumber) {
        // System.out.println("Checking if user exists with NIC/Passport: " + nicPassport + " and Account: " + accountNumber);
        // return dbManager.checkUserExists(nicPassport, accountNumber);
        return true;
    }

    // public User getUserDetailsIfRegistered(String nicPassport) {
    //     System.out.println("Fetching user details for NIC/Passport: " + nicPassport);
    //     return profileManager.getUserByNicPassport(nicPassport);
    // }
}