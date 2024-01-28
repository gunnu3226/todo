package com.sparta.todo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
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
