package com.etiya.rentacar.business.dtos.requests;

import com.etiya.rentacar.entities.concretes.Role;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest
{
    private String email;
    private String password;
    private LocalDate birthDate;
    private Role role;
}
