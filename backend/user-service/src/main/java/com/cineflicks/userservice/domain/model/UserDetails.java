package com.cineflicks.userservice.domain.model;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDate;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDetails {
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
}
