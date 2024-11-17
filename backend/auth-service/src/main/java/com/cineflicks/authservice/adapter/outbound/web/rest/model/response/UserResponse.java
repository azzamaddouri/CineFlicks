package com.cineflicks.authservice.adapter.outbound.web.rest.model.response;


import com.cineflicks.authservice.domain.model.Role;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UserResponse {
    private String id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private Boolean accountLocked;
    private Boolean enabled;
    private Set<Role> roles;
    private LocalDateTime createdAt;
}

