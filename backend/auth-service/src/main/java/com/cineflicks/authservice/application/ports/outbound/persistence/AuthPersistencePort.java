package com.cineflicks.authservice.application.ports.outbound.persistence;

import com.cineflicks.authservice.domain.model.Token;
import com.cineflicks.authservice.domain.model.UserDTO;

public interface AuthPersistencePort {
    void sendValidationEmail(UserDTO user);

    Token validateTokenExists(String token);

    boolean isTokenExpired(Token token);

    void validateToken(Token token);

    String login(UserDTO user);
}
