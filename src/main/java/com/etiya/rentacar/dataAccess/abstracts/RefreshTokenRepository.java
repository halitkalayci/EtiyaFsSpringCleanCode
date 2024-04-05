package com.etiya.rentacar.dataAccess.abstracts;

import com.etiya.rentacar.entities.concretes.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Integer> {
  Optional<RefreshToken> findByToken(String token);
}
