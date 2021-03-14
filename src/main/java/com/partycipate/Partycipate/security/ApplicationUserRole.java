package com.partycipate.Partycipate.security;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Role;

import java.util.Set;

public enum ApplicationUserRole {
    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet());

    private final Set<ApplicationUserPermission> permissionSet;

    ApplicationUserRole(Set<ApplicationUserPermission> permissionSet){
        this.permissionSet=permissionSet;
    }
}
