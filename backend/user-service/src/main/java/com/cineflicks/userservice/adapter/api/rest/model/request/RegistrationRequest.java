package com.cineflicks.userservice.adapter.api.rest.model.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    @NotEmpty(message = "Field firstname cannot be empty or null.")
    @NotBlank(message = "Field firstname cannot be empty or null.")
    private String firstname;
    @NotEmpty(message = "Field lastname cannot be empty or null.")
    @NotBlank(message = "Field lastname cannot be empty or null.")
    private String lastname;
    @Email(message = "Email is not formatted")
    @NotEmpty(message = "Field email cannot be empty or null.")
    @NotBlank(message = "Field email cannot be empty or null.")
    private String email;
    @NotEmpty(message = "Field password cannot be empty or null.")
    @NotBlank(message = "Field password cannot be empty or null.")
    @Size(min = 8, message = "Password should be 8 characters long minimum.")
    private String password;
}
