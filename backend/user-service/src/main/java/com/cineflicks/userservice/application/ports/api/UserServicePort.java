package com.cineflicks.userservice.application.ports.api;

import com.cineflicks.userservice.domain.model.User;

public interface UserServicePort {

    User register(User user);

}
