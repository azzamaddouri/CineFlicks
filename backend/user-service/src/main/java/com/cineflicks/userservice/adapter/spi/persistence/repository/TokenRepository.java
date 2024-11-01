package com.cineflicks.userservice.adapter.spi.persistence.repository;

import com.cineflicks.userservice.adapter.spi.persistence.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenEntity, Long > {

    Optional<TokenEntity> findByToken(String token);
}
