package com.cineflicks.authservice.adapter.inbound.web.rest.model.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
}
