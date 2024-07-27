package com.github.resalner.javapractice.request;

import com.github.resalner.javapractice.model.Status;
import java.util.Date;
import com.github.resalner.javapractice.annotation.StatusAnnotation;
import jakarta.validation.constraints.NotBlank;

public record OrderRequest(
		
		@NotBlank(message = "Необходимо указать пользователя")
		long userId,

		@NotBlank(message = "Необходимо указать дату")
		Date orderDate,

		@NotBlank(message = "Необходимо указать цену")
		double totalPrice,

		@NotBlank(message = "Необходимо указать статус")
		@StatusAnnotation
		Status status,

		@NotBlank(message = "Необходимо указать адрес")
		long addressId,

		String comments
) {}