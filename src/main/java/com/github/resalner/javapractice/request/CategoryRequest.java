package com.github.resalner.javapractice.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
		
		@NotBlank(message = "Заполните поле название")
		String name
){}