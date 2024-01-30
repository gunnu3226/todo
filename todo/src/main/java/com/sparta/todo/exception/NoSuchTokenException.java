package com.sparta.todo.exception;

public class NoSuchTokenException extends RuntimeException {
    public NoSuchTokenException(String message) {
        super(message);
    }
}
