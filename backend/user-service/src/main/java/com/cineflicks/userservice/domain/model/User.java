package com.cineflicks.userservice.domain.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private String email;
    private String password;
    private boolean accountLocked;
    private boolean enabled;
    private List<Role> roles;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

}
