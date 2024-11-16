package com.cineflicks.authservice.domain.model;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Boolean enabled;
    private List<Role> roles;

    public String fullName() {
        return firstname + " " + lastname;
    }
}
