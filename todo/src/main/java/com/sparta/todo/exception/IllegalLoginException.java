package com.sparta.todo.exception;

public class IllegalLoginException extends RuntimeException {
    public IllegalLoginException(String message) {
        super(message);
    }
}
