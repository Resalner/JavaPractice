package com.github.resalner.javapractice.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.EnumSet;

import com.github.resalner.javapractice.model.Role;

public class RoleValidator implements ConstraintValidator<RoleAnnotation, Role> {

	@Override
	public void initialize(RoleAnnotation constraintAnnotation) {
	}

	@Override
	public boolean isValid(Role role, ConstraintValidatorContext context) {
		return role != null && EnumSet.allOf(Role.class).contains(role);
	}
}