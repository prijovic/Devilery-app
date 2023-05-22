package com.ftn.sbnz.model.models;

import org.springframework.security.core.GrantedAuthority;

public enum Permission implements GrantedAuthority {
    USER_CRUD;

    @Override
    public String getAuthority() {
        return name();
    }
}
