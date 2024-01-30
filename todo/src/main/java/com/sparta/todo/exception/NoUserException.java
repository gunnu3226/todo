package com.sparta.todo.exception;

public class NoUserException extends RuntimeException{
    public NoUserException(String message) {
        super(message);
    }
}
