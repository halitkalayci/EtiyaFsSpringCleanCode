package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.AuthService;
import com.etiya.rentacar.business.abstracts.UserService;
import com.etiya.rentacar.business.dtos.requests.LoginRequest;
import com.etiya.rentacar.business.messages.AuthMessages;
import com.etiya.rentacar.core.services.JwtService;
import com.etiya.rentacar.core.utilities.exceptions.types.BusinessException;
import com.etiya.rentacar.dataAccess.abstracts.RefreshTokenRepository;
import com.etiya.rentacar.entities.concretes.RefreshToken;
import com.etiya.rentacar.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Ref;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class AuthManager implements AuthService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public String login(LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        if(!authentication.isAuthenticated())
            throw new BusinessException(AuthMessages.LOGIN_FAILED);

        User user = userService.findByUsername(request.getEmail());
        Map<String,Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("id",user.getId());

        String jwt = jwtService.generateToken(claims, request.getEmail());

        // TODO: Create Refresh Token For Specific User
        RefreshToken token = new RefreshToken();
        token.setUser(user);
        token.setToken("abc"); // TODO:Refactor
        token.setExpirationDate(LocalDateTime.now().plusDays(10));

        refreshTokenRepository.save(token);

        return jwt;
    }

    @Override
    public String refreshToken(String refreshToken) {
        // TODO: RefreshToken-User
        RefreshToken token = refreshTokenRepository
                .findByToken(refreshToken)
                .orElseThrow(() -> new BusinessException("Refresh token not found.."));

        // Tüm şartlar sağlanıyo ise
        if(token.getExpirationDate().isBefore(LocalDateTime.now()))
            throw new BusinessException("Refresh token expired.");

        if(token.getRevokedDate() != null)
            throw new BusinessException("Refresh token revoked.");
        // Yeni Jwt Üret
        // Yeni Refresh Token Üret
        // Eski refresh tokenı düzenle.
        User user = token.getUser();
        Map<String,Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("id",user.getId());
        String jwt = jwtService.generateToken(claims, user.getEmail());


        // RefreshToken kullanıldı.
        return jwt;
    }
}
