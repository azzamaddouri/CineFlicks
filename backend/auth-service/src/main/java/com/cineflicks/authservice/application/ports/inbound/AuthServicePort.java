package com.cineflicks.authservice.application.ports.inbound;

import com.cineflicks.authservice.domain.model.UserDTO;


public interface AuthServicePort {
    UserDTO register(UserDTO user);

    void activeAccount(String token);

    String login(UserDTO user);
}
