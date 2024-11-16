package com.cineflicks.authservice.adapter.outbound.web.rest.model.response;


import com.cineflicks.authservice.domain.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private String id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private Boolean enabled;
    private List<Role> roles;
}

