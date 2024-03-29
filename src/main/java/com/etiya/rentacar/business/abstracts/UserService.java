package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.RegisterRequest;

public interface UserService
{
    void register(RegisterRequest request);
}
