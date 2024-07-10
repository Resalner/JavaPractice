package com.github.resalner.javapractice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such user exists")
public class NoSuchUserExistsException extends RuntimeException {
    public NoSuchUserExistsException(String message) {
        super(message);
    }

    public NoSuchUserExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
