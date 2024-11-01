package com.cineflicks.userservice.application.ports.spi.persistence;

import com.cineflicks.userservice.domain.model.User;

public interface UserPersistencePort {
    User register(User user);
}
