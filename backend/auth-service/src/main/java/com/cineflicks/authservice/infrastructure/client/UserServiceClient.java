package com.cineflicks.authservice.infrastructure.client;


import com.cineflicks.authservice.domain.model.UserDTO;


public interface UserServiceClient {
    UserDTO getUserByEmail(String email);
}

