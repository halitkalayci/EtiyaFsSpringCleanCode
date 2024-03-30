package com.etiya.rentacar.dataAccess.abstracts;

import com.etiya.rentacar.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Derived Query Methods
    Optional<User> findUserByEmail(String email);
}
