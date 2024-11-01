package com.cineflicks.userservice.adapter.spi.persistence;

import com.cineflicks.userservice.adapter.spi.persistence.mapper.RolePersistenceMapper;
import com.cineflicks.userservice.adapter.spi.persistence.mapper.UserPersistenceMapper;
import com.cineflicks.userservice.adapter.spi.persistence.repository.RoleRepository;
import com.cineflicks.userservice.adapter.spi.persistence.repository.UserRepository;
import com.cineflicks.userservice.application.ports.spi.persistence.UserPersistencePort;
import com.cineflicks.userservice.domain.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final RolePersistenceMapper rolePersistenceMapper;
    private final UserPersistenceMapper userPersistenceMapper;
    private final PasswordEncoder passwordEncoder;


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
}
