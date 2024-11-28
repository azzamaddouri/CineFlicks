package com.cineflicks.authservice.domain.exception;

import lombok.Getter;

@Getter
public class UserServiceException extends RuntimeException {
    private final String error;
    private final String businessErrorDescription;

    public UserServiceException(String error, String businessErrorDescription) {
        super(error);
        this.error = error;
        this.businessErrorDescription = businessErrorDescription;
    }

}
