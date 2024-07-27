package com.github.resalner.javapractice.request;

import jakarta.validation.constraints.NotBlank;

public record ProductRequest(
		
		@NotBlank(message = "Не заполнено поле имя")
		String name,

		@NotBlank(message = "Не заполнено поле описание")
		String description,

		@NotBlank(message = "Не заполнено поле цена")
		Double price,

		@NotBlank(message = "Не заполнено поле категория")
		long categoryId
) {}