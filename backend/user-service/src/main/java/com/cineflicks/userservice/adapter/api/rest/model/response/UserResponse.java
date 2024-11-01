package com.cineflicks.userservice.adapter.api.rest.model.response;

import com.cineflicks.userservice.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

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
