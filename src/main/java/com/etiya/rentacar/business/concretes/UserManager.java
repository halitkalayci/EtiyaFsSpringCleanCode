package com.etiya.rentacar.business.concretes;

import com.etiya.rentacar.business.abstracts.UserService;
import com.etiya.rentacar.business.dtos.requests.RegisterRequest;
import com.etiya.rentacar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentacar.dataAccess.abstracts.UserRepository;
import com.etiya.rentacar.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserManager implements UserService
{
    private final UserRepository userRepository;
    private final ModelMapperService modelMapperService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequest request) {
        // TODO: Business Rule, Validation
        User user = modelMapperService.forRequest().map(request,User.class);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }
}
