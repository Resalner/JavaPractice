package com.github.resalner.javapractice.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.github.resalner.javapractice.model.Status;

public class StatusValidator implements ConstraintValidator<StatusAnnotation, Status> {

    @Override
    public void initialize(StatusAnnotation constraintAnnotation) {
    }

    @Override
    public boolean isValid(Status status, ConstraintValidatorContext context) {
        return status != null && 
               (status == Status.PENDING || 
                status == Status.SHIPPED || 
                status == Status.DELIVERED || 
                status == Status.CANCELED);
    }
}
