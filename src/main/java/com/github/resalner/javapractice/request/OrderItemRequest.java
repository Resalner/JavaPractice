package com.github.resalner.javapractice.request;

import jakarta.validation.constraints.NotNull;

public record OrderItemRequest(
		
		@NotNull(message = "Необходимо указать номер заказа")
		Long orderId,

		@NotNull(message = "Необходимо указать номер товара")
		Long productId,

		@NotNull(message = "Заполните поле количество")
		Integer count,

		@NotNull(message = "Заполните поле цена")
		Double price
) {}