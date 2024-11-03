package com.cineflicks.userservice.adapter.api.rest.mapper;

import com.cineflicks.userservice.adapter.api.rest.model.request.AuthenticationRequest;
import com.cineflicks.userservice.adapter.api.rest.model.request.RegistrationRequest;
import com.cineflicks.userservice.adapter.api.rest.model.response.AuthenticationResponse;
import com.cineflicks.userservice.adapter.api.rest.model.response.UserResponse;
import com.cineflicks.userservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRestMapper {
    User toUser(RegistrationRequest request);
    UserResponse toUserResponse(User user);
    User toUserr(AuthenticationRequest request);

    default AuthenticationResponse toAuthenticationResponse(String jwtToken) {
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
