package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.RefreshTokenService;
import com.etiya.rentacar.core.utilities.exceptions.types.BusinessException;
import com.etiya.rentacar.dataAccess.abstracts.RefreshTokenRepository;
import com.etiya.rentacar.entities.concretes.RefreshToken;
import com.etiya.rentacar.entities.concretes.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RefreshTokenManager implements RefreshTokenService {
 private final RefreshTokenRepository refreshTokenRepository;


  @Override
  public RefreshToken create(User user) {
    RefreshToken token = new RefreshToken();
    token.setUser(user);
    token.setToken("abc"); // TODO:Refactor (use jwtService.generateToken() with longer expiration time)
    token.setExpirationDate(LocalDateTime.now().plusDays(10));
    refreshTokenRepository.save(token);
    return token;
  }

  @Override
  public RefreshToken verifyRefreshToken(String token) {
    RefreshToken refreshToken = refreshTokenRepository
            .findByToken(token)
            .orElseThrow(() -> new BusinessException("Refresh token not found.."));

    // Tüm şartlar sağlanıyo ise
    if(refreshToken.getExpirationDate().isBefore(LocalDateTime.now()))
      throw new BusinessException("Refresh token expired.");

    if(refreshToken.getRevokedDate() != null)
      throw new BusinessException("Refresh token revoked.");

    return refreshToken;
  }

  private void revokeAllTokens(User user)
  {
  // TODO: Revoke all previous user tokens..
  }
}
