package com.cineflicks.userservice.domain.model;

import jakarta.persistence.Embedded;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String username;
    private String email;
    private String password;
    private boolean accountLocked;
    private boolean enabled;
    private Set<Role> roles;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedDate;

    @Embedded
    private UserDetails userDetails;
}
