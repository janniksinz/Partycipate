package com.partycipate.Partycipate.security;

import com.google.common.collect.Sets;
import static com.partycipate.Partycipate.security.ApplicationUserPermission.*;
import java.util.Set;

public enum ApplicationUserRole {
    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(SURVEY_WRITE, SURVEY_READ));

    private final Set<ApplicationUserPermission> permissionSet;

    ApplicationUserRole(Set<ApplicationUserPermission> permissionSet){
        this.permissionSet=permissionSet;
    }

    public Set<ApplicationUserPermission> getPermissionSet() {
        return permissionSet;
    }
}
