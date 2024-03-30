package com.etiya.rentacar.api.controllers;

import com.etiya.rentacar.business.abstracts.AuthService;
import com.etiya.rentacar.business.dtos.requests.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request)
    {
        return authService.login(request);
    }
}
