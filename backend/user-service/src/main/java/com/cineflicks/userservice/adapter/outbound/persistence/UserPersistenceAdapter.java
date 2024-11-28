package com.cineflicks.userservice.adapter.outbound.persistence;

import com.cineflicks.userservice.adapter.outbound.persistence.entity.UserEntity;
import com.cineflicks.userservice.adapter.outbound.persistence.mapper.UserPersistenceMapper;
import com.cineflicks.userservice.adapter.outbound.persistence.repository.RoleRepository;
import com.cineflicks.userservice.adapter.outbound.persistence.repository.UserRepository;
import com.cineflicks.userservice.application.ports.outbound.persistence.UserPersistencePort;
import com.cineflicks.userservice.domain.exception.AlreadyExistsException;
import com.cineflicks.userservice.domain.exception.NotFoundException;
import com.cineflicks.userservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserPersistenceAdapter implements UserPersistencePort {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserPersistenceMapper persistenceMapper;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public User save(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new AlreadyExistsException("User already exists with email: " + user.getEmail());
        }

        var userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Role USER was not initialized."));

        user.setUsername(generateUniqueUsername(
                user.getUserDetails().getFirstname(),
                user.getUserDetails().getLastname()));

        return persistenceMapper.toUser(
                userRepository.save(UserEntity.builder()
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .password(passwordEncoder.encode(user.getPassword()))
                        .accountLocked(false)
                        .enabled(false)
                        .roles(Set.of(userRole))
                        .userDetails(
                                persistenceMapper.toUserDetails(user.getUserDetails()))
                        .build()));
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id)
                .map(persistenceMapper::toUser)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(persistenceMapper::toUser)
                .orElseThrow(() -> new NotFoundException("User not found with email: " + email));
    }

    @Transactional
    @Override
    public User enable(User user) {
        var userEntity = userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + user.getId()));
        userEntity.setEnabled(true);
        return persistenceMapper.toUser(userRepository.save(userEntity));
    }

    private String generateUniqueUsername(String firstName, String lastName) {
        String base = (firstName + lastName).toLowerCase().replaceAll("\\s+", "");
        String timestamp = String.valueOf(Instant.now().toEpochMilli());

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest((base + timestamp).getBytes());
            return base + "_" + Base64.getUrlEncoder().withoutPadding().encodeToString(hash).substring(0, 8);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Error generating username", e);
        }
    }
}
