package com.cineflicks.userservice.adapter.spi.persistence.mapper;

import com.cineflicks.userservice.adapter.spi.persistence.entity.UserEntity;
import com.cineflicks.userservice.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    UserEntity toUserEntity(User user);

    User toUser(UserEntity entity);

}
