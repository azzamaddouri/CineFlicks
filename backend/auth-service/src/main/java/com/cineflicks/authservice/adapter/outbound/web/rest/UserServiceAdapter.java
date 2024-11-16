package com.cineflicks.authservice.adapter.outbound.web.rest;

import com.cineflicks.authservice.adapter.inbound.web.rest.model.response.RequestResponse;
import com.cineflicks.authservice.adapter.outbound.web.rest.model.request.EnableUserRequest;
import com.cineflicks.authservice.adapter.inbound.web.rest.model.request.RegisterRequest;
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


    @Override
    public UserDTO save(UserDTO user) {
        // Create a RegisterRequest object from the UserDTO
        RegisterRequest request = RegisterRequest.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

        // Use ParameterizedTypeReference for the response
        ResponseEntity<RequestResponse<UserResponse>> responseEntity = restTemplate.exchange(
                url + "/save",
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<RequestResponse<UserResponse>>() {}
        );

        // Get the response body
        RequestResponse<UserResponse> response = responseEntity.getBody();
        System.out.println("Response from User Service: " + response);

        // Check if the response is successful
        if (response != null && "success".equals(response.getStatus())) {
            UserResponse userResponse = response.getData();
            return UserDTO.builder()
                    .id(userResponse.getId())
                    .email(userResponse.getEmail())
                    .build();
        }
        return null; // or handle error appropriately
    }






    @Override
    public UserDTO getUserByEmail(String email) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("email", email);

        // Create an HttpEntity with no body and no headers (can add headers if necessary)
        HttpEntity<?> entity = new HttpEntity<>(null);

        // Send the GET request using restTemplate.exchange
        ResponseEntity<RequestResponse<UserResponse>> responseEntity = restTemplate.exchange(
                url + "/getUserByEmail/{email}",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<RequestResponse<UserResponse>>() {},
                uriVariables
        );

        // Get the response body
        RequestResponse<UserResponse> response = responseEntity.getBody();
        System.out.println("Response from User Service: " + response);

        // Check if the response is successful
        if (response != null && "success".equals(response.getStatus())) {
            UserResponse userResponse = response.getData();
            return UserDTO.builder()
                    .id(userResponse.getId())
                    .email(userResponse.getEmail())
                    .password(userResponse.getPassword())
                    .enabled(userResponse.getEnabled())
                    .roles(userResponse.getRoles())
                    .build();
        }

        return null;
    }



    @Override
    public UserDTO getUserById(String userId) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("id", userId);

        // Create an HttpEntity with no body and no headers (can add headers if necessary)
        HttpEntity<?> entity = new HttpEntity<>(null);

        // Send the GET request using restTemplate.exchange
        ResponseEntity<RequestResponse<UserResponse>> responseEntity = restTemplate.exchange(
                url + "/getUserById/{id}",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<RequestResponse<UserResponse>>() {},
                uriVariables
        );

        // Get the response body
        RequestResponse<UserResponse> response = responseEntity.getBody();
        System.out.println("Response from User Service: " + response);

        // Check if the response is successful
        if (response != null && "success".equals(response.getStatus())) {
            UserResponse userResponse = response.getData();
            return UserDTO.builder()
                    .id(userResponse.getId())
                    .firstname(userResponse.getFirstname())
                    .lastname(userResponse.getLastname())
                    .email(userResponse.getEmail())
                    .enabled(userResponse.getEnabled())
                    .build();
        }

        return null;
    }


    @Override
    public void enable(String userId, Boolean enabled) {
        // Create EnableUserRequest with userId and enabled status
        EnableUserRequest request = EnableUserRequest.builder()
                .id(userId)
                .enabled(enabled)
                .build();

        // Send PUT request with the request body
        ResponseEntity<RequestResponse<UserResponse>> responseEntity = restTemplate.exchange(
                url + "/enable",
                HttpMethod.PUT,
                new HttpEntity<>(request),  // Pass the request object as the body
                new ParameterizedTypeReference<RequestResponse<UserResponse>>() {}  // Use UserResponse instead of Any
        );

        // Get the response body
        RequestResponse<UserResponse> response = responseEntity.getBody();
        System.out.println("Response from User Service: " + response);

        // Handle the response if needed
        if (response != null && "success".equals(response.getStatus())) {
            // Success handling if needed
        } else {
            // Error handling if needed
        }
    }


}
