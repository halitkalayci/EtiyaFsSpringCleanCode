package com.etiya.rentacar.api.controllers;

import com.etiya.rentacar.business.abstracts.UserService;
import com.etiya.rentacar.business.dtos.requests.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;


    @PostMapping
    public void register(@RequestBody  RegisterRequest request)
    {
        userService.register(request);
    }
}
