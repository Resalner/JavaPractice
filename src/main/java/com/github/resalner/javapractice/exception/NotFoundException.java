package com.github.resalner.javapractice.exception;

public class NotFoundException extends RuntimeException {
    private String message;

    public NotFoundException() {}

    public NotFoundException(String msg) {
        super(msg);
        this.message = msg;
    }
}