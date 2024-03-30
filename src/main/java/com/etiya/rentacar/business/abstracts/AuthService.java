package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.business.dtos.requests.LoginRequest;

public interface AuthService {
    String login(LoginRequest request);
}
