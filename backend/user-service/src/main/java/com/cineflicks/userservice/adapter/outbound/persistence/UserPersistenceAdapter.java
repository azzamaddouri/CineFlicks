package com.cineflicks.userservice.adapter.outbound.persistence;

import com.cineflicks.userservice.adapter.outbound.persistence.entity.UserDetailsEntity;
import com.cineflicks.userservice.adapter.outbound.persistence.entity.UserEntity;
import com.cineflicks.userservice.adapter.outbound.persistence.mapper.UserPersistenceMapper;
import com.cineflicks.userservice.adapter.outbound.persistence.repository.RoleRepository;
import com.cineflicks.userservice.adapter.outbound.persistence.repository.UserRepository;
import com.cineflicks.userservice.application.ports.outbound.persistence.UserPersistencePort;
import com.cineflicks.userservice.domain.model.User;
import com.cineflicks.userservice.domain.model.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;
import java.util.List;


@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserPersistenceMapper persistenceMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User save(User user) {
        var userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("ROLE USER was not initialized."));
        System.out.println("Retrieved Role: " + userRole);


        user.setUsername(generateUniqueUsername(
                user.getUserDetails().getFirstname(),
                user.getUserDetails().getLastname()));
     var  user1 = UserEntity.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .userDetails(UserDetailsEntity.builder()
                        .firstname(user.getUserDetails().getFirstname())
                        .lastname(user.getUserDetails().getLastname())
                        .dateOfBirth(user.getUserDetails().getDateOfBirth())
                        .build())
                .build();
        var saveduser = persistenceMapper.toUser( userRepository.save(user1));
                System.out.println("Retrieved user: " + saveduser.getId());
      return saveduser;

    }


    @Override
    public User getUserById(String id) {
        return persistenceMapper.toUser(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }


    @Override
    public User getUserByEmail(String email) {
        var user1 = userRepository.findByEmail(email);
        System.out.println(user1.toString());
        var user2= persistenceMapper.toUser(userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found")));
        System.out.println(user2.getRoles().toString());
        return user2;
    }



    @Transactional
    @Override
    public void enable(User user) {
        var userEntity = userRepository.findById(user.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        userEntity.setEnabled(user.isEnabled());
        userRepository.save(userEntity);
    }

    public String generateUniqueUsername(String firstName, String lastName) {
        String base = (firstName + lastName).toLowerCase().replaceAll("\\s+", "");
        String timestamp = String.valueOf(Instant.now().toEpochMilli());

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest((base + timestamp).getBytes());
            String encodedHash = Base64.getUrlEncoder().withoutPadding().encodeToString(hash);

            return base + "_" + encodedHash.substring(0, 8);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating username", e);
        }
    }
}
