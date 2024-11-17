package com.cineflicks.userservice.adapter.outbound.persistence.mapper;

import com.cineflicks.userservice.adapter.outbound.persistence.entity.UserDetailsEntity;
import com.cineflicks.userservice.adapter.outbound.persistence.entity.UserEntity;
import com.cineflicks.userservice.domain.model.User;
import com.cineflicks.userservice.domain.model.UserDetails;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserPersistenceMapper {
    User toUser(UserEntity userEntity);
    UserDetailsEntity toUserDetails(UserDetails entity);
}





