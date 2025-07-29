package com.jimmyproject.bookingservice.enums;
/**
 * MUST BE KEPT IN SYNC WITH:
 * user-service: com.jimmyproject.userservice.enums.Roles
 * Last synced: 2025-07-28
 */
public enum Roles {
    CUSTOMER,
    ADMIN,
    MANAGER,
    UNKNOWN;

    public static Roles fromString(String role) {
        try {
            return Roles.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
