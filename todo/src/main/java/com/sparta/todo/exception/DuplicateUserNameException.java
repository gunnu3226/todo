package com.sparta.todo.exception;

public class DuplicateUserNameException extends RuntimeException {
    public DuplicateUserNameException(String message) {
        super(message);
    }
}
