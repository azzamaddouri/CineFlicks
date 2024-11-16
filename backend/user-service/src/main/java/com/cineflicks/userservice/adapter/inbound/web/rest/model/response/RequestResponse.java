package com.cineflicks.userservice.adapter.inbound.web.rest.model.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestResponse<T> {
    private String status;
    private String message;
    private T data;
}
