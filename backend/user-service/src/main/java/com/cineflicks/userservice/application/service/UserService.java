package com.cineflicks.userservice.application.service;

import com.cineflicks.userservice.application.ports.inbound.UserServicePort;
import com.cineflicks.userservice.application.ports.outbound.persistence.UserPersistencePort;
import com.cineflicks.userservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserServicePort {

    private final UserPersistencePort persistencePort;

    @Override
    public User save(User user) {
       return persistencePort.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return persistencePort.getUserByEmail(email);
    }

    @Override
    public User getUserById(String id) {
        return persistencePort.getUserById(id);
    }

    @Override
    public User enable(User user) {
        return persistencePort.enable(user);
    }
}
