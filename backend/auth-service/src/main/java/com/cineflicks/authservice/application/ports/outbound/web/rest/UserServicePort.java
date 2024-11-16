package com.cineflicks.authservice.application.ports.outbound.web.rest;

import com.cineflicks.authservice.domain.model.UserDTO;

public interface UserServicePort {

    UserDTO save(UserDTO user);

    UserDTO getUserByEmail( String email);

    UserDTO getUserById(String userId);

    void enable(String userId, Boolean enabled);

}
