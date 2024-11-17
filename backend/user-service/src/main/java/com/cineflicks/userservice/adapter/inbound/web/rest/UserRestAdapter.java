package com.cineflicks.userservice.adapter.inbound.web.rest;

import com.cineflicks.userservice.adapter.inbound.web.rest.mapper.UserRestMapper;
import com.cineflicks.userservice.adapter.inbound.web.rest.model.request.EnableUserRequest;
import com.cineflicks.userservice.adapter.inbound.web.rest.model.request.RegisterRequest;
import com.cineflicks.userservice.adapter.inbound.web.rest.model.response.RequestResponse;
import com.cineflicks.userservice.adapter.inbound.web.rest.model.response.UserResponse;
import com.cineflicks.userservice.application.ports.inbound.UserServicePort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserRestAdapter {

    private final UserServicePort servicePort;
    private final UserRestMapper restMapper;

    @PostMapping("/save")
    public ResponseEntity<RequestResponse<UserResponse>> save(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RequestResponse.<UserResponse>builder()
                        .status("created")
                        .message("User successfully registered.")
                        .data(restMapper.toUserResponse(
                                servicePort.save(restMapper.toUser(request))))
                        .build());
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<RequestResponse<UserResponse>> getUserById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(RequestResponse.<UserResponse>builder()
                .status("retrieved")
                .message("User successfully retrieved by ID.")
                .data(restMapper.toUserResponse(servicePort.getUserById(id)))
                .build());
    }

    @PutMapping("/enable")
    public ResponseEntity<RequestResponse<UserResponse>> enable(@Valid @RequestBody EnableUserRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(RequestResponse.<UserResponse>builder()
                .status("enabled")
                .message("User account successfully enabled.")
                .data(restMapper.toUserResponse(
                        servicePort.enable(restMapper.toUser(request))))
                .build());
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<RequestResponse<UserResponse>> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(RequestResponse.<UserResponse>builder()
                        .status("retrieved")
                        .message("User successfully retrieved by email.")
                        .data(restMapper.toUserResponse(servicePort.getUserByEmail(email)))
                        .build());
    }
}
