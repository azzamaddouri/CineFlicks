package com.cineflicks.userservice.domain.model;

import jakarta.persistence.Embedded;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedDate;
    private String username;
    private String email;
    private String password;
    private boolean accountLocked;
    private boolean enabled;
    private List<Role> roles;

    @Embedded
    private UserDetails userDetails;
}
