package com.github.resalner.javapractice.map;

import com.github.resalner.javapractice.model.Order;
import com.github.resalner.javapractice.dto.OrderResponse;
import com.github.resalner.javapractice.request.OrderRequest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    OrderResponse toResponse(Order order);

    List<OrderResponse> toDomain(List<Order> orders);

    Order toOrder(OrderRequest orderRequest);
}