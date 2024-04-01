package com.etiya.rentacar.entities.concretes;


import com.etiya.rentacar.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
               joinColumns = @JoinColumn(name="user_id"),
               inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> authorities;

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
// Unit Test