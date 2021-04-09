package com.partycipate.Partycipate.security;

public enum ApplicationUserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    ADMIN_WRITE("admin:write"),
    ADMIN_READ("admin:read"),
    PARTICIPANT_READ("participant:read"),
    PARTICIPANT_WRITE("participant:write");

    private  final String permissions;

    ApplicationUserPermission(String permissions){
        this.permissions=permissions;
    }

    public String getPermission() {
        return permissions;
    }
}
