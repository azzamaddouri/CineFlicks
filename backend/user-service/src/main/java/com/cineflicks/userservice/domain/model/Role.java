package com.cineflicks.userservice.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private String id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedDate;
}
