package com.cineflicks.userservice.adapter.outbound.persistence.mapper;

import com.cineflicks.userservice.adapter.outbound.persistence.entity.UserEntity;
import com.cineflicks.userservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserPersistenceMapper {
    User toUser(UserEntity userEntity);
    UserEntity toUserEntity(User user);
}





