package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.RegisterRequest;
import com.etiya.rentacar.entities.concretes.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService
{
    void register(RegisterRequest request);
    User findByUsername(String username);
}
