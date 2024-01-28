package com.sparta.todo.controller;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Data
public class ResponseBody {

    private int statusCode;
    private HttpStatus status;
    private String message;
    private Object data;

    public ResponseBody(HttpStatus status, String message, Object data) {
        this.statusCode = status.value();
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
