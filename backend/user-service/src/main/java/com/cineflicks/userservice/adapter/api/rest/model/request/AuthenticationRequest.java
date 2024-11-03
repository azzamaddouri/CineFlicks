package com.cineflicks.userservice.adapter.api.rest.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @Email(message = "Email is not formatted")
    @NotEmpty(message = "Field email cannot be empty or null.")
    @NotBlank(message = "Field email cannot be empty or null.")
    private String email;
    @NotEmpty(message = "Field password cannot be empty or null.")
    @NotBlank(message = "Field password cannot be empty or null.")
    @Size(min = 8, message = "Password should be 8 characters long minimum.")
    private String password;
}
