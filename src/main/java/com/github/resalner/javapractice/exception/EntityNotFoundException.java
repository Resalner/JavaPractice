package com.github.resalner.javapractice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EntityNotFoundException extends RuntimeException {
	
	private String message;
	
    public EntityNotFoundException(String message) {
        super(message);
        this.message = message;
    }
    
}
