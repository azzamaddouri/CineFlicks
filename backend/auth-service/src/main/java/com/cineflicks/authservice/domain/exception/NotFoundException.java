package com.cineflicks.authservice.domain.exception;


public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}