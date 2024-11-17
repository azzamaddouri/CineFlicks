package com.cineflicks.authservice.adapter.outbound.persistence.mapper;

import com.cineflicks.authservice.adapter.outbound.persistence.entity.TokenEntity;
import com.cineflicks.authservice.domain.model.Token;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthPersistenceMapper {
    TokenEntity toTokenEntity(Token token);

    Token toToken(TokenEntity entity);
}
