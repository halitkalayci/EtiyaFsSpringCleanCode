package com.etiya.rentacar.entities.concretes;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN,
    MODERATOR,
    CUSTOMER;

    // ADMIN == ADMIN
    @Override
    public String getAuthority()
    {
        return name();
    }
}
