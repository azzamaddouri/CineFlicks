package com.cineflicks.authservice.infrastructure.security.jwt;

import com.cineflicks.authservice.infrastructure.client.UserServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserServiceClient userServiceClient;

    @Override
    public UserDetails loadUserByUsername(String userEmail) {
        var user = userServiceClient.getUserByEmail(userEmail);
        return new CustomUserDetails(user);
    }
}
