package com.cineflicks.authservice.adapter.inbound.web.rest.model.response;

import com.cineflicks.authservice.domain.model.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private String id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private Set<Role> roles;
    private LocalDateTime createdAt;
}

