package com.ftn.sbnz.model.models;

import org.springframework.security.core.GrantedAuthority;

public enum Permission implements GrantedAuthority {
    USER_CRUD,
    RESTAURANT_CRUD,

    CREATE_EMPLOYEE,
    ORDER_STATUS_UPDATE,
    READ_RESTAURANT_ORDERS,

    DELIVERY_ORDER_STATUS_UPDATE,
    READ_DELIVERER_ORDERS;

    @Override
    public String getAuthority() {
        return name();
    }
}
