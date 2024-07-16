package com.github.resalner.javapractice.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class RoleValidator implements ConstraintValidator<RoleAnnotation, String> {

	@Override
	public void initialize(RoleAnnotation constraintAnnotation) {
	}

	@Override
	public boolean isValid(String role, ConstraintValidatorContext context) {
		List<String> roles = Arrays.asList("CUSTOMER", "ADMIN");
		return roles.contains(role);
	}

}