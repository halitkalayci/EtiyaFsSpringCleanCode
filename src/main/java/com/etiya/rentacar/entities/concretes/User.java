package com.etiya.rentacar.entities.concretes;


import com.etiya.rentacar.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity<Integer> implements UserDetails
{
    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="birthDate")
    private LocalDate birthDate;

    @Column(name="roles")
    @Enumerated(EnumType.STRING)
    @JoinTable(name="roles", joinColumns = @JoinColumn(name="role_id"))
    private List<Role> authorities;

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
