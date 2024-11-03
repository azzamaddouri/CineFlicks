package com.cineflicks.userservice.application.ports.spi.persistence;

import com.cineflicks.userservice.domain.model.Token;
import com.cineflicks.userservice.domain.model.User;

public interface UserPersistencePort {
    User register(User user);

    String authenticate(User user);

    Token validateTokenExists(String token);

    boolean isTokenExpired(Token token);

    void enable(Long userId);

    void validateToken(Token token);
}
