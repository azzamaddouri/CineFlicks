package com.cineflicks.authservice.application.service;

import com.cineflicks.authservice.application.ports.inbound.AuthServicePort;
import com.cineflicks.authservice.application.ports.outbound.persistence.AuthPersistencePort;
import com.cineflicks.authservice.application.ports.outbound.web.rest.UserServicePort;
import com.cineflicks.authservice.domain.model.UserDTO;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthService implements AuthServicePort {

    private final AuthPersistencePort persistencePort;
    private final UserServicePort servicePort;

    @Override
    @Transactional(rollbackFor = MessagingException.class)
    public UserDTO register(UserDTO user) {
        var savedUser = servicePort.save(user);
        persistencePort.sendValidationEmail(savedUser);
        return savedUser;
    }

    @Override
    public void activeAccount(String token) {
        var savedToken = persistencePort.validateTokenExists(token);

        var user = servicePort.getUserById(savedToken.getUserId());

        if (persistencePort.isTokenExpired(savedToken)) {
           persistencePort.sendValidationEmail(user);
            throw new RuntimeException("Activation token has expired. A new token has been sent to the same email address.");
        }

        servicePort.enable(user.getId(),true);

        persistencePort.validateToken(savedToken);

    }

    @Override
    public String login(UserDTO user) {
        return persistencePort.login(user);
    }
}
