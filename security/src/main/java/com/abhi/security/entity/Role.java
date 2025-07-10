package com.abhi.security.entity;

import lombok.Getter;

@Getter
public enum Role {

    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_DEV("ROLE_DEV"),
    ROLE_TESTER("ROLE_TESTER"),
    ROLE_DEVOPS("ROLE_DEVOPS"),
    ROLE_SUPER_ADMIN("ROLE_SUPERADMIN");

    private final String value;

    Role(String value) {
        this.value = value;
    }

}
