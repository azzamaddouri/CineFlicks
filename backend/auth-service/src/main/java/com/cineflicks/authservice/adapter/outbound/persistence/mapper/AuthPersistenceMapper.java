package com.cineflicks.authservice.adapter.outbound.persistence.mapper;

import com.cineflicks.authservice.adapter.inbound.web.rest.model.response.RegisterResponse;
import com.cineflicks.authservice.adapter.outbound.persistence.entity.TokenEntity;
import com.cineflicks.authservice.domain.model.Token;
import com.cineflicks.authservice.domain.model.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthPersistenceMapper {

    UserDTO toUserDTO(RegisterResponse response);

    TokenEntity toTokenEntity(Token token);

    Token toToken(TokenEntity entity);

}
