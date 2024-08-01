package com.github.resalner.javapractice.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

import com.github.resalner.javapractice.model.Role;

public class RoleValidator implements ConstraintValidator<RoleAnnotation, Role> {

	@Override
	public void initialize(RoleAnnotation constraintAnnotation) {
	}

	@Override
    public boolean isValid(Role role, ConstraintValidatorContext context) {
        return role != null && (role == Role.CUSTOMER || role == Role.ADMIN);
    }
}