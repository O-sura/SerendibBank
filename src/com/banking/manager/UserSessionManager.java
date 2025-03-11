package com.banking.manager;

import com.banking.domain.User;
import com.banking.state.UserContext;
import com.banking.util.AuditLogger;

public class UserSessionManager {
    private static UserSessionManager instance;
    private UserContext userContext;
    
    private UserSessionManager() {
        userContext = new UserContext();
    }
    
    public static synchronized UserSessionManager getInstance() {
        if (instance == null) {
            instance = new UserSessionManager();
        }
        return instance;
    }
    
    public UserContext getUserContext() {
        return userContext;
    }
    
    public User getCurrentUser() {
        return userContext.getCurrentUser();
    }
    
    public void setCurrentUser(User user) {
        userContext.setCurrentUser(user);
        AuditLogger.getInstance().log("SESSION", "User set to: " + 
            (user != null ? user.getUsername() : "null"));
    }
    
    public boolean isUserAuthenticated() {
        return userContext.isAuthenticated();
    }
    
    public void setUserAuthenticated(boolean authenticated) {
        userContext.setAuthenticated(authenticated);
    }
    
    public String getOnboardingStage() {
        return userContext.getOnboardingStage();
    }
    
    public void setOnboardingStage(String stage) {
        userContext.setOnboardingStage(stage);
    }
    
    public void clearSession() {
        AuditLogger.getInstance().log("SESSION", "Session cleared for user: " + 
            (userContext.getCurrentUser() != null ? userContext.getCurrentUser().getUsername() : "unknown"));
        userContext.reset();
    }

    public void setSessionData(String key, String value) {
        userContext.setSessionData(key, value);
    }

    public String getSessionData(String key) {
        return userContext.getSessionData(key);
    }
}