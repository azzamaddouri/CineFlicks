package com.cineflicks.authservice.domain.exception;

import lombok.Getter;

import java.util.Set;

@Getter
public class BadRequestException extends RuntimeException {
    private final Set<String> validationErrors;

    public BadRequestException(String message, Set<String> validationErrors) {
        super(message);
        this.validationErrors = validationErrors;
    }

}
