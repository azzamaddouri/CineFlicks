package com.cineflicks.userservice.adapter.outbound.persistence.repository;

import com.cineflicks.userservice.adapter.outbound.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, String> {
    Optional<RoleEntity> findByName(String role);
}
