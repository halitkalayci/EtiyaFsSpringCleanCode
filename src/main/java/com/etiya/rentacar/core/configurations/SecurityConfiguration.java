package com.etiya.rentacar.core.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration
{
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        // halit123
        // ABLBWQK!^)'=)DSV3124091231
        return new BCryptPasswordEncoder();
    }
}
// 9:15