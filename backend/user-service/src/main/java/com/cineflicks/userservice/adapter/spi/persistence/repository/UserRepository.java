package com.cineflicks.userservice.adapter.spi.persistence.repository;

import com.cineflicks.userservice.adapter.spi.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
}
