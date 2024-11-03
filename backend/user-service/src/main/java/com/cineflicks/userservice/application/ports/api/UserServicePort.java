package com.cineflicks.userservice.application.ports.api;

import com.cineflicks.userservice.domain.model.Token;
import com.cineflicks.userservice.domain.model.User;

public interface UserServicePort {

    User register(User user);
    String authenticate(User user);

    void activeAccount(String token);
}
