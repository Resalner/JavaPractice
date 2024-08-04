package com.github.resalner.javapractice.request;

import com.github.resalner.javapractice.model.Status;
import java.util.Date;
import com.github.resalner.javapractice.annotation.StatusAnnotation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderRequest(
		
		@NotNull(message = "Необходимо указать пользователя")
		Long userId,

		@NotNull(message = "Необходимо указать дату")
		Date orderDate,

		@NotNull(message = "Необходимо указать цену")
		Double totalPrice,

		@StatusAnnotation
		Status status,

		@NotNull(message = "Необходимо указать адрес")
		Long addressId,

		String comments
) {}