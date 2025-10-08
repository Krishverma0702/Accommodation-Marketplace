package com.dcl.accommodate.service.impl;

import com.dcl.accommodate.model.User;
import com.dcl.accommodate.repository.UserRepository;
import com.dcl.accommodate.service.contracts.UserService;
import org.springframework.stereotype.Service;

import com.dcl.accommodate.dto.request.UserRegistrationRequest;
import com.dcl.accommodate.enums.UserRoles;
import com.dcl.accommodate.exception.UserAlreadyExistByEmailException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public void registerUser(UserRegistrationRequest registration) {
        if (repository.existsByEmail(registration.email()))
            throw new UserAlreadyExistByEmailException("User already registered with such email ID");
        var user = this.toUser(registration);
        //All users are GUEST by default
        user.setUserRole(UserRoles.GUEST);
        repository.save(user);
    }

    private User toUser(UserRegistrationRequest registration) {
        return User.builder()
                .firstName(registration.firstName())
                .lastName(registration.lastName())
                .email(registration.email())
                .phoneNumber(registration.phoneNumber())
                .password(registration.password())
                .dateOfBirth(registration.dateOfBirth())
                .build();
    }
}

