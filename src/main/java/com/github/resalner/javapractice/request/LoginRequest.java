package com.github.resalner.javapractice.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

	@NotBlank(message = "Необходимо ввести логин")
	private String username;

	@NotBlank(message = "Необходимо ввести пароль")
	private String password;
}