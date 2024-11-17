package com.cineflicks.authservice.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
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

    public String fullName() {
        return firstname + " " + lastname;
    }
}
