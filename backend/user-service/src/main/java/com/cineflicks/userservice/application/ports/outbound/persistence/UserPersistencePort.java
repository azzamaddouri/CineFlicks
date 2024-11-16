package com.cineflicks.userservice.application.ports.outbound.persistence;

import com.cineflicks.userservice.domain.model.User;

public interface UserPersistencePort {

    User save(User user);

    User getUserByEmail(String email);

    User getUserById(String id);

    void enable(User user);

}
