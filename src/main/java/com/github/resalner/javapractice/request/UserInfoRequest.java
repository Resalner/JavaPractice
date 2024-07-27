package com.github.resalner.javapractice.request;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record UserInfoRequest(
		
		@NotBlank(message = "Необходимо указать имя")
		@Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Имя может содержать только буквы")
		String name,

		@NotBlank(message = "Необходимо указать фамилию")
		@Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Имя может содержать только буквы")
		String surname,

		@NotBlank(message = "Необходимо указать номер телефона")
		@Pattern(regexp = "^375(33|44|29|25)\\d{7}$", message = "неверный формат номера телефона")
		String phoneNumber,

		@NotBlank(message = "Необходимо указать дату рождения")
		@Past(message = "Дата рождения должна быть в прошлом")
		Date birthDate,

		@NotBlank(message = "Необходимо указать пол")
		boolean gender,

		@NotBlank(message = "Необходимо указать email")
		@Email(message = "Необходимо указать корректный email")
		String email
) {}