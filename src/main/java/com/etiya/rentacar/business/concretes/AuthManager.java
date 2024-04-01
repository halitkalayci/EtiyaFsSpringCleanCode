package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.AuthService;
import com.etiya.rentacar.business.abstracts.UserService;
import com.etiya.rentacar.business.dtos.requests.LoginRequest;
import com.etiya.rentacar.business.messages.AuthMessages;
import com.etiya.rentacar.core.services.JwtService;
import com.etiya.rentacar.core.utilities.exceptions.types.BusinessException;
import com.etiya.rentacar.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

        UserDetails user = userService.loadUserByUsername(request.getEmail());
        // .. JWT'ye eklemek istediğiniz tüm bilgiler.
        Map<String,Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("id",1);

        String jwt = jwtService.generateToken(claims, request.getEmail());
        return jwt;
    }
}
