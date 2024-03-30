package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.AuthService;
import com.etiya.rentacar.business.abstracts.UserService;
import com.etiya.rentacar.business.dtos.requests.LoginRequest;
import com.etiya.rentacar.business.messages.AuthMessages;
import com.etiya.rentacar.core.services.JwtService;
import com.etiya.rentacar.core.utilities.exceptions.types.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthManager implements AuthService {
    private final UserService userService;
    //private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public String login(LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        if(!authentication.isAuthenticated())
            throw new BusinessException(AuthMessages.LOGIN_FAILED);

        String jwt = jwtService.generateToken(request.getEmail());
        return jwt;
    }
}
