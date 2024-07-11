package com.github.resalner.javapractice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User info not found")
public class UserInfoNotFoundException extends RuntimeException {
    public UserInfoNotFoundException(String message) {
        super(message);
    }

    public UserInfoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
