package com.github.resalner.javapractice.dto;

public record OrderItemResponse(
	long id, 
	long productId, 
	long orderId, 
	Integer count, 
	double price
) {
}