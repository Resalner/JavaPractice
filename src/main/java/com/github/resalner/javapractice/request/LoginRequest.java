package com.github.resalner.javapractice.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
		@NotBlank(message = "Необходимо ввести логин")
		String username,

		@NotBlank(message = "Необходимо ввести пароль")
		String password) 
{}