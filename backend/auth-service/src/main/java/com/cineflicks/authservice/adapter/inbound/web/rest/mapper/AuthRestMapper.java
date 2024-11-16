package com.cineflicks.authservice.adapter.inbound.web.rest.mapper;

import com.cineflicks.authservice.adapter.inbound.web.rest.model.request.LoginRequest;
import com.cineflicks.authservice.adapter.inbound.web.rest.model.request.RegisterRequest;
import com.cineflicks.authservice.adapter.inbound.web.rest.model.response.LoginResponse;
import com.cineflicks.authservice.adapter.inbound.web.rest.model.response.RegisterResponse;
import com.cineflicks.authservice.domain.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthRestMapper {

    UserDTO toUserDTO(RegisterRequest request);

    RegisterResponse toRegisterResponse(UserDTO user);

    UserDTO toUserDTO(LoginRequest request);

    default LoginResponse toLoginResponse(String token) {
        return LoginResponse.builder()
                .token(token)
                .build();
    }


}
