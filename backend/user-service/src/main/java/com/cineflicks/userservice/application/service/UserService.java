package com.cineflicks.userservice.application.service;

import com.cineflicks.userservice.application.ports.api.UserServicePort;
import com.cineflicks.userservice.application.ports.spi.event.EmailValidationPort;
import com.cineflicks.userservice.application.ports.spi.persistence.UserPersistencePort;
import com.cineflicks.userservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserServicePort {

    private final UserPersistencePort userPersistencePort;
    private final EmailValidationPort emailValidationPort;

    @Override
    public User register(User user) {
        User savedUser = userPersistencePort.register(user);
        emailValidationPort.sendValidationEmail(savedUser);
        return savedUser;
    }

}
