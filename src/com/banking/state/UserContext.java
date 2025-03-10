package com.banking.state;

import java.util.Map;

import com.banking.domain.User;

public class UserContext {
    private User currentUser;
    private String sessionId;
    private long sessionStartTime;
    private boolean isAuthenticated = false;
    private String onboardingStage;
    private Map<String, String> sessionData;
    
    public UserContext() {
        this.sessionStartTime = System.currentTimeMillis();
        this.sessionId = generateSessionId();
        this.sessionData = new java.util.HashMap<>();
    }
    
    private String generateSessionId() {
        return "session-" + System.currentTimeMillis() + "-" + Math.random();
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
    
    public boolean isAuthenticated() {
        return isAuthenticated;
    }
    
    public void setAuthenticated(boolean authenticated) {
        this.isAuthenticated = authenticated;
    }
    
    public String getSessionId() {
        return sessionId;
    }
    
    public long getSessionDuration() {
        return System.currentTimeMillis() - sessionStartTime;
    }
    
    public String getOnboardingStage() {
        return onboardingStage;
    }
    
    public void setOnboardingStage(String stage) {
        this.onboardingStage = stage;
    }
    
    public void reset() {
        currentUser = null;
        isAuthenticated = false;
        onboardingStage = null;
        sessionId = generateSessionId();
        sessionStartTime = System.currentTimeMillis();
        sessionData.clear();
    }

    public String getSessionData(String key) {
        return sessionData.get(key);
    }

    public void setSessionData(String key, String value) {
        sessionData.put(key, value);
    }


}