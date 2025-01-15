package com.barber.admin.enums.database;

public enum DatabaseName {
    ADMIN_DB("admin_db"),
    USER_DB("user_db"),
    ORDER_DB("order_db");

    private final String dbName;

    DatabaseName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return dbName;
    }
}
