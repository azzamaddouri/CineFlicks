package com.cineflicks.authservice.domain.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.http.HttpStatus.*;

@Slf4j
public class UserServiceResponseErrorHandler extends DefaultResponseErrorHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        HttpStatusCode statusCode = response.getStatusCode();
        String responseBody;

        try {
            responseBody = new String(response.getBody().readAllBytes());
        } catch (IOException e) {
            log.error("Failed to read response body. Status code: {}", statusCode, e);
            responseBody = "";
        }

        log.error("Error response received. Status code: {}, Body: {}", statusCode, responseBody);

        if (statusCode.is4xxClientError() || statusCode.is5xxServerError()) {
            try {
                JsonNode errorNode = objectMapper.readTree(responseBody);
                String error = errorNode.path("error").asText("");
                String businessErrorDescription = errorNode.path("businessErrorDescription").asText("");
                JsonNode validationErrorsNode = errorNode.path("validationErrors");
                if (statusCode == CONFLICT) {
                    throw new AlreadyExistsException(error);
                } else if (statusCode == NOT_FOUND) {
                    throw new NotFoundException(error);
                } else if (statusCode == BAD_REQUEST) {
                    Set<String> validationErrors = new HashSet<>();
                    validationErrorsNode.forEach(node -> validationErrors.add(node.asText()));
                    throw new BadRequestException("Validation errors occurred", validationErrors);
                }

                throw new UserServiceException(businessErrorDescription, error);

            } catch (JsonProcessingException e) {
                log.warn("Failed to parse error response as JSON. Response body: {}", responseBody, e);
                throw new RuntimeException("Error response received: " + responseBody, e);
            }
        }

        super.handleError(response);
    }
}

