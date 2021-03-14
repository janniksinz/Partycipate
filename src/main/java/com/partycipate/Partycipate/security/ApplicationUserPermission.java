package com.partycipate.Partycipate.security;

public enum ApplicationUserPermission {
    SURVEY_READ("admin:read"),
    SURVEY_WRITE("admin:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission=permission;
    }

    public String getPermission() {
        return permission;
    }
}
