package com.github.resalner.javapractice.request;

import jakarta.validation.constraints.NotBlank;

public record ProductRequest(
		
		@NotBlank(message = "Не заполнено поле имя")
		String name,

		@NotBlank(message = "Не заполнено поле описание")
		String description,

		Double price,

		Long categoryId
) {}