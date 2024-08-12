package com.github.resalner.javapractice.map;

import com.github.resalner.javapractice.model.OrderItem;
import com.github.resalner.javapractice.dto.OrderItemResponse;
import com.github.resalner.javapractice.request.OrderItemRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper {
	
	@Mapping(source = "order.id", target = "orderId")
	@Mapping(source = "product.id", target = "productId")
    OrderItemResponse toResponse(OrderItem orderItem);
	
	@Mapping(source = "orderId", target = "order.id")
	@Mapping(source = "productId", target = "product.id")
    OrderItem toOrderItem(OrderItemRequest orderItemRequest);

    List<OrderItemResponse> toDomain(List<OrderItem> orderItem);
}