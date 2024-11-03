package com.cineflicks.userservice.adapter.spi.persistence.mapper;
import com.cineflicks.userservice.adapter.spi.persistence.entity.TokenEntity;
import com.cineflicks.userservice.domain.model.Token;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenPersistenceMapper {

    Token toToken(TokenEntity entity);

    TokenEntity toTokenEntity(Token token);
}

