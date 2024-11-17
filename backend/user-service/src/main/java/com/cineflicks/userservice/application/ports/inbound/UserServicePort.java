package com.cineflicks.userservice.application.ports.inbound;


import com.cineflicks.userservice.domain.model.User;

public interface UserServicePort {

    User save(User user);

    User getUserByEmail(String email);

    User getUserById(String id);

    User enable(User user);

}
