package com.cineflicks.userservice.adapter.spi.persistence.mapper;
import com.cineflicks.userservice.adapter.spi.persistence.entity.TokenEntity;
import com.cineflicks.userservice.domain.model.Token;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenPersistenceMapper {
    TokenEntity toTokenEntity(Token token);
}
