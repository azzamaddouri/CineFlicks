package com.cineflicks.userservice.adapter.spi.persistence.mapper;

import com.cineflicks.userservice.adapter.spi.persistence.entity.RoleEntity;
import com.cineflicks.userservice.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolePersistenceMapper {
    @Mapping(target = "users", ignore = true)
    Role toRole(RoleEntity roleEntity);

    List<Role> toRoleList(List<RoleEntity> roleEntities);
}

