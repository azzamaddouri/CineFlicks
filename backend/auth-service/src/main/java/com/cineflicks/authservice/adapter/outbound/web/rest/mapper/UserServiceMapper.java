package com.cineflicks.authservice.adapter.outbound.web.rest.mapper;

import com.cineflicks.authservice.adapter.inbound.web.rest.model.request.RegisterRequest;
import com.cineflicks.authservice.adapter.outbound.web.rest.model.response.UserResponse;
import com.cineflicks.authservice.domain.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface UserServiceMapper {
    RegisterRequest toRegisterRequest(UserDTO user);

    UserDTO toUserDTO(UserResponse response);
}
