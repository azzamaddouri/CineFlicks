package com.cineflicks.userservice.adapter.spi.persistence.repository;

import com.cineflicks.userservice.adapter.spi.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String role);
}
