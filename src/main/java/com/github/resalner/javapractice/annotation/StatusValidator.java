package com.github.resalner.javapractice.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class StatusValidator implements ConstraintValidator<StatusAnnotation, String> {
    
	@Override
	public void initialize(StatusAnnotation constraintAnnotation) {
	}
	
	@Override
    public boolean isValid(String status, ConstraintValidatorContext context) {
        List<String> Statuses = Arrays.asList("PENDING", "SHIPPED", "DELIVERED", "CANCELED");
        return Statuses.contains(status);
    }

}