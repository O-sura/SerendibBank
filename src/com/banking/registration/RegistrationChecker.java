package com.banking.registration;

import com.banking.domain.User;
import com.banking.util.DatabaseManager;
import com.banking.manager.UserProfileManager;

public class RegistrationChecker {
    private DatabaseManager dbManager;
    private UserProfileManager profileManager;

    public boolean checkUserExists(String nicPassport) {
        System.out.println("Checking if user exists with NIC/Passport: " + nicPassport);
        int userExists =  dbManager.executeQuery("Checking Whether User Already Exists with credentials: [" + nicPassport + "]");
        return userExists > 0;
    }

    public User getUserDetailsIfRegistered(String nicPassport) {
        System.out.println("Fetching user details for NIC/Passport: " + nicPassport);
        return profileManager.getUserByNicPassport(nicPassport);
    }

    public void setDbManager(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void setProfileManager(UserProfileManager profileManager) {
        this.profileManager = profileManager;
    }
}