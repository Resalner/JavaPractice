package com.github.resalner.javapractice.request;

import com.github.resalner.javapractice.model.Status;
import java.util.Date;
import com.github.resalner.javapractice.annotation.StatusAnnotation;
import jakarta.validation.constraints.NotNull;

public record OrderRequest(
		
		@NotNull(message = "Необходимо указать пользователя")
		Long userId,

		@NotNull(message = "Заполните поле дата")
		Date orderDate,

		@NotNull(message = "Заполните поле цена")
		Double totalPrice,

		@StatusAnnotation
		Status status,

		@NotNull(message = "Заполните поле адрес")
		Long addressId,

		String comments
) {}