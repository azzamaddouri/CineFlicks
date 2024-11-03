package com.cineflicks.userservice.adapter.spi.persistence;

import com.cineflicks.userservice.adapter.spi.persistence.entity.UserEntity;
import com.cineflicks.userservice.adapter.spi.persistence.mapper.RolePersistenceMapper;
import com.cineflicks.userservice.adapter.spi.persistence.mapper.TokenPersistenceMapper;
import com.cineflicks.userservice.adapter.spi.persistence.mapper.UserPersistenceMapper;
import com.cineflicks.userservice.adapter.spi.persistence.repository.RoleRepository;
import com.cineflicks.userservice.adapter.spi.persistence.repository.TokenRepository;
import com.cineflicks.userservice.adapter.spi.persistence.repository.UserRepository;
import com.cineflicks.userservice.application.ports.spi.persistence.UserPersistencePort;
import com.cineflicks.userservice.domain.model.Token;
import com.cineflicks.userservice.domain.model.User;
import com.cineflicks.userservice.infrastructure.security.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final RolePersistenceMapper rolePersistenceMapper;
    private final UserPersistenceMapper userPersistenceMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenPersistenceMapper tokenPersistenceMapper;
    private final TokenRepository tokenRepository;

    @Override
    public User register(User user) {
        var userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("ROLE USER was not initialized."));

       return userPersistenceMapper.toUser( userRepository.save(userPersistenceMapper.toUserEntity(
                User.builder()
                        .firstname(user.getFirstname())
                        .lastname(user.getLastname())
                        .email(user.getEmail())
                        .password(passwordEncoder.encode(user.getPassword()))
                        .accountLocked(false)
                        .enabled(false)
                        .roles(rolePersistenceMapper.toRoleList(List.of(userRole)))
                        .build()
        )));

    }

    @Override
    public String authenticate(User user) {

        UserEntity userEntity = userPersistenceMapper.toUserEntity(user);

        var authenticationToken = new UsernamePasswordAuthenticationToken(userEntity.getEmail(), userEntity.getPassword());
        var authResult = authenticationManager.authenticate(authenticationToken);

        UserEntity authenticatedUser = (UserEntity) authResult.getPrincipal();

        var claims = new HashMap<String, Object>();
        claims.put("fullName", authenticatedUser.fullName());

        return jwtService.generateToken(claims, authenticatedUser);
    }


    @Override
    public Token validateTokenExists(String token) {
        return tokenPersistenceMapper.toToken(tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token: the provided token does not match any records.")));
    }


    @Override
    public boolean isTokenExpired(Token token) {
        return LocalDateTime.now().isAfter(token.getExpiredAt());
    }

    @Transactional
    @Override
    public void enable(Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void validateToken(Token token) {
        token.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(tokenPersistenceMapper.toTokenEntity(token));
    }



}
