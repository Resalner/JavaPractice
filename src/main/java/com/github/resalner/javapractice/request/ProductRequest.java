package com.github.resalner.javapractice.request;

import jakarta.validation.constraints.NotBlank;

public record ProductRequest(
		
		@NotBlank(message = "Заполните поле название")
		String name,

		@NotBlank(message = "Заполните поле описание")
		String description,

		Double price,

		Long categoryId
) {}