package com.cineflicks.authservice.adapter.inbound.web.rest;

import com.cineflicks.authservice.adapter.inbound.web.rest.mapper.AuthRestMapper;
import com.cineflicks.authservice.adapter.inbound.web.rest.model.request.LoginRequest;
import com.cineflicks.authservice.adapter.inbound.web.rest.model.request.RegisterRequest;
import com.cineflicks.authservice.adapter.inbound.web.rest.model.response.LoginResponse;
import com.cineflicks.authservice.adapter.inbound.web.rest.model.response.RegisterResponse;
import com.cineflicks.authservice.adapter.inbound.web.rest.model.response.RequestResponse;
import com.cineflicks.authservice.application.ports.inbound.AuthServicePort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Any;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthRestAdapter {

    private final AuthServicePort servicePort;
    private final AuthRestMapper restMapper;

    @PostMapping("/register")
    public ResponseEntity<RequestResponse<RegisterResponse>> register(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RequestResponse.<RegisterResponse>builder()
                        .status("created")
                        .message("User registered successfully.")
                        .data(restMapper.toRegisterResponse(
                                servicePort.register(restMapper.toUserDTO(request))))
                        .build());
    }

    @GetMapping("/activate-account")
    public ResponseEntity<RequestResponse<Any>> confirm(@RequestParam String token) {
        servicePort.activeAccount(token);
        return ResponseEntity.status(HttpStatus.OK)
                .body(RequestResponse.<Any>builder()
                        .status("activated")
                        .message("Account successfully activated.")
                        .data(null)
                        .build());
    }

    @PostMapping("/login")
    public ResponseEntity<RequestResponse<LoginResponse>> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(RequestResponse.<LoginResponse>builder()
                        .status("logged_in")
                        .message("User logged in successfully.")
                        .data(restMapper.toLoginResponse(
                                servicePort.login(restMapper.toUserDTO(request))))
                        .build());
    }
}
