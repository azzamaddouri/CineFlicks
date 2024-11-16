package com.cineflicks.userservice.adapter.inbound.web.rest.mapper;

import com.cineflicks.userservice.adapter.inbound.web.rest.model.request.EnableUserRequest;
import com.cineflicks.userservice.adapter.inbound.web.rest.model.request.RegisterRequest;
import com.cineflicks.userservice.adapter.inbound.web.rest.model.response.UserResponse;
import com.cineflicks.userservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRestMapper {

        @Mapping(target = "userDetails.firstname", source = "firstname")
        @Mapping(target = "userDetails.lastname", source = "lastname")
        User toUser(RegisterRequest request);

        @Mapping(target = "firstname", source = "userDetails.firstname")
        @Mapping(target = "lastname", source = "userDetails.lastname")
        UserResponse toUserResponse(User user);

        User toUser(EnableUserRequest request);

}

