package com.cineflicks.userservice.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedDate;
    private String name;
}
