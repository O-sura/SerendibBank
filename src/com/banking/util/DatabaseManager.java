package com.banking.util;

public class DatabaseManager {
    private static DatabaseManager dbInstance;
//    private DatabaseConnection dbConnection;

    private  DatabaseManager() {}

    // Method to get the database instance
    public static DatabaseManager getInstance() {
        if (dbInstance == null) {
            try {
                dbInstance = new DatabaseManager();
            } catch (Exception e) {
                throw new RuntimeException("Failed to create the database instance", e);
            }
        }
        return dbInstance;
    }

    //Executes a given query and returns the results
    public int executeQuery(String query) {
        System.out.println("Executing the query for: " + query);
        return 0;
    }

}
