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

    private Long id;
    private String name;
    private List<Long> roleIds;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

}
