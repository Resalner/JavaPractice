package com.github.resalner.javapractice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Category already exists")
public class NoSuchCategoryExistsException extends RuntimeException {
    public NoSuchCategoryExistsException(String message) {
        super(message);
    }

    public NoSuchCategoryExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
