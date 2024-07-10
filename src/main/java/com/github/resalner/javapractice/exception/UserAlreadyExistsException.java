package com.github.resalner.javapractice.exception;

public class UserAlreadyExistsException extends RuntimeException {
    private String message;

    public UserAlreadyExistsException() {}

    public UserAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}

