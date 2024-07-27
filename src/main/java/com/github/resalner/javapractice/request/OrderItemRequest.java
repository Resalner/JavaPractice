package com.github.resalner.javapractice.request;

import jakarta.validation.constraints.NotBlank;

public record OrderItemRequest(
		
		@NotBlank(message = "Необходимо указать номер заказа")
		long orderId,

		@NotBlank(message = "Необходимо указать номер товара")
		long productId,

		@NotBlank(message = "Необходимо указать количество")
		Integer count,

		@NotBlank(message = "Необходимо указать цену")
		Double price
) {}