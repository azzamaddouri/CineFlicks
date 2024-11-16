package com.cineflicks.authservice.application.ports.outbound.persistence;

import com.cineflicks.authservice.domain.model.Token;
import com.cineflicks.authservice.domain.model.UserDTO;
import org.springframework.transaction.annotation.Transactional;

public interface AuthPersistencePort {

    String login(UserDTO user);

    void sendValidationEmail(UserDTO user);

    Token validateTokenExists(String token);

    boolean isTokenExpired(Token token);

    @Transactional
    void validateToken(Token token);
}
