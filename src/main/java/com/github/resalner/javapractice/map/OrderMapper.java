package com.github.resalner.javapractice.map;

import com.github.resalner.javapractice.model.Order;
import com.github.resalner.javapractice.dto.OrderResponse;
import com.github.resalner.javapractice.request.OrderRequest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

	@Mapping(source = "user.id", target = "userId")
	@Mapping(source = "address.id", target = "addressId")
	OrderResponse toResponse(Order order);

	List<OrderResponse> toDomain(List<Order> orders);

	@Mapping(source = "userId", target = "user.id")
	@Mapping(source = "addressId", target = "address.id")
	Order toOrder(OrderRequest orderRequest);
}