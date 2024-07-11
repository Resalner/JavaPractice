package com.github.resalner.javapractice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User info is invalid")
public class UserInfoInvalidException extends RuntimeException {
    public UserInfoInvalidException(String message) {
        super(message);
    }

    public UserInfoInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
