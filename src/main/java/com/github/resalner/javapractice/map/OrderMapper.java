package com.github.resalner.javapractice.map;

import com.github.resalner.javapractice.model.Order;
import com.github.resalner.javapractice.response.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "orderDate", source = "orderDate", dateFormat = "dd.MM.yyyy")
    @Mapping(target = "totalPrice", source = "totalPrice")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "OrderItemId", source = "OrderItemId")
    @Mapping(terget = "comments", source = "comments")
    Order toOrder(OrderResponse orderResponse);
}