package com.cineflicks.userservice.adapter.spi.persistence.mapper;

import com.cineflicks.userservice.adapter.spi.persistence.entity.RoleEntity;
import com.cineflicks.userservice.adapter.spi.persistence.entity.UserEntity;
import com.cineflicks.userservice.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RolePersistenceMapper {
    @Mapping(target = "roleIds", source = "users")
    Role toRole(RoleEntity roleEntity);

    default List<Long> map(List<UserEntity> users) {
        return users.stream()
                .map(UserEntity::getId)
                .collect(Collectors.toList());
    }

    List<Role> toRoleList(List<RoleEntity> roleEntities);
}
