package com.github.resalner.javapractice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;


@Target(value = {ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {RoleValidator.class})
public @interface RoleAnnotation {
    public String message() default "Неверная роль";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}