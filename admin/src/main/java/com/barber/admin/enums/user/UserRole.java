package com.barber.admin.enums.user;

import lombok.Getter;

@Getter
public enum UserRole {
    OWNER("Owner"),
    BARBER("Barber");

    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public static UserRole fromDisplayName(String displayName) {
        for (UserRole role : UserRole.values()) {
            if (role.displayName.equalsIgnoreCase(displayName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role display name: " + displayName);
    }

}
