package com.partycipate.Partycipate.security;

import com.google.common.collect.Sets;
import static com.partycipate.Partycipate.security.ApplicationUserPermission.*;
import java.util.Set;

public enum ApplicationUserRole {
    USER(Sets.newHashSet(SURVEY_READ, USER_READ, ANSWER_READ, RESULT_READ, ANALYSIS_READ)),
    ADMIN(Sets.newHashSet(SURVEY_WRITE, SURVEY_READ, USER_READ, USER_WRITE, ANSWER_READ, ANSWER_WRITE, RESULT_READ, ANALYSIS_READ)),
    PARTICIPANT(Sets.newHashSet(ANSWER_WRITE, RESULT_READ));

    private final Set<ApplicationUserPermission> permissionSet;

    ApplicationUserRole(Set<ApplicationUserPermission> permissionSet){
        this.permissionSet=permissionSet;
    }

    public Set<ApplicationUserPermission> getPermissionSet() {
        return permissionSet;
    }
}
