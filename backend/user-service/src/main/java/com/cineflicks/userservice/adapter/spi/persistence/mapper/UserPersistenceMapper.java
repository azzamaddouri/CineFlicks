package com.cineflicks.userservice.adapter.spi.persistence.mapper;

import com.cineflicks.userservice.adapter.spi.persistence.entity.UserEntity;
import com.cineflicks.userservice.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    User toUser(UserEntity entity);

    UserEntity toUserEntity(User user);
}
