package com.cineflicks.userservice.domain.model;
import jakarta.persistence.Embeddable;
import lombok.*;


@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDetails {
    private String firstname;
    private String lastname;
}
