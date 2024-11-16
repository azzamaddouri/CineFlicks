package com.cineflicks.userservice.adapter.inbound.web.rest;

import com.cineflicks.userservice.adapter.inbound.web.rest.mapper.UserRestMapper;
import com.cineflicks.userservice.adapter.inbound.web.rest.model.request.EnableUserRequest;
import com.cineflicks.userservice.adapter.inbound.web.rest.model.request.RegisterRequest;
import com.cineflicks.userservice.adapter.inbound.web.rest.model.response.RequestResponse;
import com.cineflicks.userservice.adapter.inbound.web.rest.model.response.UserResponse;
import com.cineflicks.userservice.application.ports.inbound.web.rest.UserServicePort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Any;
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

        return ResponseEntity.ok(
                RequestResponse.<UserResponse>builder()
                        .status("success")
                        .message("User saved successfully.")
                        .data(restMapper.toUserResponse(
                                servicePort.save(restMapper.toUser(request))))
                        .build()
        );
    }


    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<RequestResponse<UserResponse>> getUserByEmail(@PathVariable String email) {
        var test =servicePort.getUserByEmail(email);
        var data = restMapper.toUserResponse(test);
        var user =  RequestResponse.<UserResponse>builder()
                .status("success")
                .message("User retrieved successfully.")
                .data(data)
                .build();
        return ResponseEntity.ok(
               user
        );
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<RequestResponse<UserResponse>> getUserById(@PathVariable String id) {
        var test = servicePort.getUserById(id);
        var data = restMapper.toUserResponse(test);
        var user =  RequestResponse.<UserResponse>builder()
                .status("success")
                .message("User retrieved successfully.")
                .data(data)
                .build();
        return ResponseEntity.ok(
                user
        );
    }


    @PutMapping("/enable")
    public ResponseEntity<RequestResponse<UserResponse>> enable(@Valid @RequestBody EnableUserRequest request) {
        servicePort.enable(restMapper.toUser(request));
        return ResponseEntity.ok(RequestResponse.<UserResponse>builder()
                .status("success")
                .message("User account enabled successfully.")
                .data(null)  // Or pass a populated UserResponse if needed
                .build());
    }


}
