package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.AuthService;
import com.etiya.rentacar.business.abstracts.UserService;
import com.etiya.rentacar.core.services.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthManager implements AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public String login() {
        // TODO: Implement
        return null;
    }
}
// Unit Test