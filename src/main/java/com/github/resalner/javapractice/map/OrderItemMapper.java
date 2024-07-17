package com.github.resalner.javapractice.map;

import com.github.resalner.javapractice.model.OrderItem;
import com.github.resalner.javapractice.dto.OrderItemResponse;
import com.github.resalner.javapractice.request.OrderItemRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper {

    OrderItemResponse toResponse(OrderItem orderItem);

    OrderItem toOrderItem(OrderItemRequest orderItemRequest);

    List<OrderItem> toDomain(List<OrderItem> orderItem);
}