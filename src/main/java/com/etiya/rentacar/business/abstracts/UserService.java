package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService
{
    void register(RegisterRequest request);
}
