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
        return status != null && (status.matches("PENDING") 
        						||status.matches("SHIPPED") 
        						||status.matches("DELIVERED") 
        						||status.matches("CANCELED"));
    }
}