package com.github.resalner.javapractice.request;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record UserInfoRequest(
		
		@NotBlank(message = "Заполните поле имя")
		@Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Имя может содержать только буквы")
		String name,

		@NotBlank(message = "Заполните поле фамилия")
		@Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Имя может содержать только буквы")
		String surname,

		@NotBlank(message = "Заполните поле номер телефона")
		@Pattern(regexp = "^375(33|44|29|25)\\d{7}$", message = "неверный формат номера телефона")
		String phoneNumber,

		@Past(message = "Дата рождения должна быть в прошлом")
		Date birthDate,

		boolean gender,

		@NotBlank(message = "Заполните поле email")
		@Email(message = "Необходимо указать корректный email")
		String email
) {}