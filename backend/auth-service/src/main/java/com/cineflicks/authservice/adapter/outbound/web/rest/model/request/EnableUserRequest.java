package com.cineflicks.authservice.adapter.outbound.web.rest.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnableUserRequest {
    private String id;
    private Boolean enabled;
}
