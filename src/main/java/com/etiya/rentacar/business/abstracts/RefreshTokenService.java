package com.etiya.rentacar.business.abstracts;

import com.etiya.rentacar.entities.concretes.RefreshToken;
import com.etiya.rentacar.entities.concretes.User;

public interface RefreshTokenService {
  RefreshToken create(User user);
  RefreshToken verifyRefreshToken(String token);
}
