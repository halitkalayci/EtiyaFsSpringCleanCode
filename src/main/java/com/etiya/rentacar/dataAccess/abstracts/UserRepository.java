package com.etiya.rentacar.dataAccess.abstracts;

import com.etiya.rentacar.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
