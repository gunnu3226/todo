package com.sparta.todo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(DuplicateUserNameException.class)
    public ResponseEntity<ErrorResponse> userExHandle(DuplicateUserNameException e) {
        log.error("[exceptionHandle] ex", e);
        ErrorResponse errorResult = new ErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResult);
    }
}
