package com.github.resalner.javapractice.request;

import jakarta.validation.constraints.NotNull;

public record OrderItemRequest(
		
		@NotNull(message = "Необходимо указать номер заказа")
		Long orderId,

		@NotNull(message = "Необходимо указать номер товара")
		Long productId,

		@NotNull(message = "Необходимо указать количество")
		Integer count,

		@NotNull(message = "Необходимо указать цену")
		Double price
) {}