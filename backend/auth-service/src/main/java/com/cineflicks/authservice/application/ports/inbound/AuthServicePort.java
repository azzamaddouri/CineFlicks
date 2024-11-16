package com.cineflicks.authservice.application.ports.inbound;

import com.cineflicks.authservice.domain.model.UserDTO;
import jakarta.mail.MessagingException;

public interface AuthServicePort {

    UserDTO register(UserDTO user);

    void activeAccount(String token);

    String login(UserDTO user);

}
