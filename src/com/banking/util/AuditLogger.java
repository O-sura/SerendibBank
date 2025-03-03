package com.banking.util;

public class AuditLogger {
    private static AuditLogger logger;
//    private DatabaseConnection dbConnection;

    private  AuditLogger() {}

    // Method to get the logger instance
    public static AuditLogger getInstance() {
        if (logger == null) {
            try {
                logger = new AuditLogger();
            } catch (Exception e) {
                throw new RuntimeException("Failed to create logger instance", e);
            }
        }
        return logger;
    }

    //Adds the log message to the log with the type of the log
    public void log(String type, String message) {
        System.out.println("[" + type.toUpperCase() + "]: " + message);
    }
}
