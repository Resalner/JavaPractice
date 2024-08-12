package com.github.resalner.javapractice.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LoginRequest(
		@NotBlank(message = "Заполните поле логин")
		@Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Имя может содержать только буквы")
		String username,

		@NotBlank(message = "Заполните поле пароль")
		@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$",
		message = "Пароль должен содержать хотя бы одну букву в верхнем и нижнем регистре и одну цифру")
		String password) 
{}