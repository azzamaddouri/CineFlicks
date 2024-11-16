package com.cineflicks.userservice.adapter.outbound.persistence.entity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDate;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetailsEntity {

    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;

}
