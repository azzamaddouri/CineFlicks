package com.cineflicks.userservice.adapter.inbound.web.rest.model.response;

import com.cineflicks.userservice.domain.model.Role;
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
