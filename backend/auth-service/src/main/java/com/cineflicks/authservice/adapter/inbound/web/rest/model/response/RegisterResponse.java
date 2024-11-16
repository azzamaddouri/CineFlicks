package com.cineflicks.authservice.adapter.inbound.web.rest.model.response;

import lombok.*;

import java.time.LocalDateTime;

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
    private LocalDateTime createdAt;

}

