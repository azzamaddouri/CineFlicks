package com.cineflicks.authservice.adapter.outbound.persistence.repository;

import com.cineflicks.authservice.adapter.outbound.persistence.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TokenRepository extends JpaRepository<TokenEntity, String> {
    Optional<TokenEntity> findByToken(String token);
}
