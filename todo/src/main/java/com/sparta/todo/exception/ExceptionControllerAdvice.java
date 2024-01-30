package com.sparta.todo.exception;

import com.sparta.todo.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(DuplicateUserNameException.class)
    public ResponseEntity<ErrorResponse> userExHandle(DuplicateUserNameException e) {
        log.info("[exceptionHandle] ex", e);
        ErrorResponse errorResult = new ErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResult);
    }

    @ExceptionHandler(IllegalLoginException.class)
    public ResponseEntity<ErrorResponse> NoUserExHandle(IllegalLoginException e) {
        log.info("[exceptionHandle] ex", e);
        ErrorResponse errorResult = new ErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResult);
    }

    @ExceptionHandler(NoSuchTokenException.class)
    public ResponseEntity<ErrorResponse> NoTokenHandle(NoSuchTokenException e) {
        log.info("[exceptionHandle] ex", e);
        ErrorResponse errorResult = new ErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResult);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> NoSuchElementHandle(NoSuchElementException e) {
        log.info("[exceptionHandle] ex", e);
        ErrorResponse errorResult = new ErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResult);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> AccessDeniedHandle(AccessDeniedException e) {
        log.info("[exceptionHandle] ex", e);
        ErrorResponse errorResult = new ErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResult);
    }
}
