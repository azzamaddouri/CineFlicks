package com.cineflicks.authservice.adapter.outbound.web.rest;

import com.cineflicks.authservice.adapter.outbound.web.rest.mapper.UserServiceMapper;
import com.cineflicks.authservice.adapter.outbound.web.rest.model.request.EnableUserRequest;
import com.cineflicks.authservice.adapter.outbound.web.rest.model.response.RequestResponse;
import com.cineflicks.authservice.adapter.outbound.web.rest.model.response.UserResponse;
import com.cineflicks.authservice.application.ports.outbound.web.rest.UserServicePort;
import com.cineflicks.authservice.domain.model.UserDTO;
import com.cineflicks.authservice.infrastructure.client.UserServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import org.springframework.http.ResponseEntity;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class UserServiceAdapter implements UserServicePort, UserServiceClient {

    private final RestTemplate restTemplate;

    @Value("${application.config.user-url}")
    private String url;

    private final UserServiceMapper serviceMapper;


    @Override
    public UserDTO save(UserDTO user) {
        ResponseEntity<RequestResponse<UserResponse>> responseEntity = restTemplate.exchange(
                url + "/save",
                HttpMethod.POST,
                new HttpEntity<>(serviceMapper.toRegisterRequest(user)),
                new ParameterizedTypeReference<>() {});

        RequestResponse<UserResponse> response = responseEntity.getBody();

        if (response == null || response.getData() == null) {
            throw new RuntimeException("Failed to save the user: no valid response received.");
        }

        return serviceMapper.toUserDTO(response.getData());
    }

    @Override
    public UserDTO getUserById(String userId) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("id", userId);

        ResponseEntity<RequestResponse<UserResponse>> responseEntity = restTemplate.exchange(
                url + "/getUserById/{id}",
                HttpMethod.GET,
                new HttpEntity<>(null),
                new ParameterizedTypeReference<>() {},
                uriVariables
        );

        RequestResponse<UserResponse> response = responseEntity.getBody();
        if (response == null || response.getData() == null) {
            throw new RuntimeException("Failed to retrieve the user by ID: no valid response received.");
        }

        return serviceMapper.toUserDTO(response.getData());
    }

    @Override
    public void enable(String userId, Boolean enabled) {
        ResponseEntity<RequestResponse<UserResponse>> responseEntity = restTemplate.exchange(
                url + "/enable",
                HttpMethod.PUT,
                new HttpEntity<>(EnableUserRequest.builder()
                                .id(userId)
                                .enabled(enabled)
                                .build()),
                new ParameterizedTypeReference<>() {});

        RequestResponse<UserResponse> response = responseEntity.getBody();

        if (response == null || response.getData() == null) {
            throw new RuntimeException("Failed to enable the user: no valid response received.");
        }
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("email", email);

        ResponseEntity<RequestResponse<UserResponse>> responseEntity = restTemplate.exchange(
                url + "/getUserByEmail/{email}",
                HttpMethod.GET,
                new HttpEntity<>(null),
                new ParameterizedTypeReference<>() {},
                uriVariables
        );

        RequestResponse<UserResponse> response = responseEntity.getBody();

        if (response == null || response.getData() == null) {
            throw new RuntimeException("Failed to retrieve the user by email: no valid response received.");
        }

        return serviceMapper.toUserDTO(response.getData());
    }

}
