package com.ftn.sbnz.model.models;

import org.springframework.security.core.GrantedAuthority;

public enum Permission implements GrantedAuthority {
    USER_CRUD,
    RESTAURANT_CRUD,
    CREATE_EMPLOYEE;

    @Override
    public String getAuthority() {
        return name();
    }
}
