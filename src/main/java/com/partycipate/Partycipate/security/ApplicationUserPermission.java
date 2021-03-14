package com.partycipate.Partycipate.security;

public enum ApplicationUserPermission {
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission=permission;
    }

    public String getPermission() {
        return permission;
    }
}
