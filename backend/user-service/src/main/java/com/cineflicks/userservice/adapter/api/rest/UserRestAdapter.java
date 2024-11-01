package com.cineflicks.userservice.adapter.api.rest;

import com.cineflicks.userservice.adapter.api.rest.mapper.UserRestMapper;
import com.cineflicks.userservice.adapter.api.rest.model.request.RegistrationRequest;
import com.cineflicks.userservice.adapter.api.rest.model.response.UserResponse;
import com.cineflicks.userservice.application.ports.api.UserServicePort;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/auth")
@Tag(name="Authentication")
public class UserRestAdapter {
    private final UserServicePort servicePort;
    private final UserRestMapper restMapper;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid RegistrationRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(restMapper.toUserResponse(
                        servicePort.register(restMapper.toUser(request))));
    }

}
