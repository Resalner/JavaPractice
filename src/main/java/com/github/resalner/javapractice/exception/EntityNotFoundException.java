package com.github.resalner.javapractice.exception;

public class EntityNotFoundException extends RuntimeException {
    private String message;

    public EntityNotFoundException() {}

    public EntityNotFoundException(String msg) {
        super(msg);
        this.message = msg;
    }
}