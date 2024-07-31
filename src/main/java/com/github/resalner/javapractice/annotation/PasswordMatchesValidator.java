package com.github.resalner.javapractice.annotation;

import com.github.resalner.javapractice.request.RegistrationDataRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		RegistrationDataRequest registrationData = (RegistrationDataRequest) value;
		return registrationData.getPassword().equals(registrationData.getMatchingPassword());
	}
}