package com.github.resalner.javapractice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StatusValidator.class)
public @interface StatusAnnotation {
	String message() default "Неверный статус";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
