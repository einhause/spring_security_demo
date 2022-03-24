package com.einhause.spring_security.security;

import java.util.Set;

import static com.einhause.spring_security.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    STUDENT(Set.of()),
    ADMIN(Set.of(STUDENT_READ, STUDENT_WRITE, COURSE_READ, COURSE_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
}
