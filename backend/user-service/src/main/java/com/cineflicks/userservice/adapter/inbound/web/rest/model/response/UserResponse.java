package com.cineflicks.userservice.adapter.inbound.web.rest.model.response;

import com.cineflicks.userservice.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private boolean accountLocked;
    private Boolean enabled;
    private Set<Role> roles;
    private LocalDateTime createdAt;
}
