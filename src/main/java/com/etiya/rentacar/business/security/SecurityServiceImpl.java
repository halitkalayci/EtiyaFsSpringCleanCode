package com.etiya.rentacar.business.security;

import com.etiya.rentacar.business.constants.Roles;
import com.etiya.rentacar.core.services.SecurityService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService
{
    private static final String[] WHITE_LIST_URLS = {
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/api/v1/auth/**",
            "/api/v1/users"
    };

    // C# => Extension Method Alternatif
    @Override
    public HttpSecurity configureSecurity(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(x-> x
                .requestMatchers(WHITE_LIST_URLS).permitAll()
                .requestMatchers(HttpMethod.GET).permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/brands").hasAnyAuthority(Roles.ADMIN, Roles.MODERATOR)
                .requestMatchers(HttpMethod.POST, "/api/v1/cars").hasAnyAuthority(Roles.ADMIN, Roles.MODERATOR)
                .anyRequest().authenticated()
        );
        return http;
    }
}
