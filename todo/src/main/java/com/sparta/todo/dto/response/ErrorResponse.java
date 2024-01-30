package com.sparta.todo.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter @Setter
public class ErrorResponse {

    private int statusCode;
    private HttpStatus status;
    private String message;

    public ErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.statusCode = status.value();
        this.message = message;
    }
}
