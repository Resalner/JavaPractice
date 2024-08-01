package com.github.resalner.javapractice.request;

import com.github.resalner.javapractice.model.Role;
import com.github.resalner.javapractice.annotation.RoleAnnotation;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserRequest(
		
		@NotBlank(message = "Необходимо указать имя")
		@Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Имя может содержать только буквы")
		String username,

		@NotBlank(message = "Необходимо указать пароль")
		@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$",
				message = "Пароль должен содержать хотя бы одну букву в верхнем и нижнем регистре и одну цифру")
		String password,

		@RoleAnnotation
		Role role
) {}