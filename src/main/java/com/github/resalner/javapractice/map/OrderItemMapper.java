package com.github.resalner.javapractice.map;

import com.github.resalner.javapractice.model.OrderItem;
import com.github.resalner.javapractice.response.OrderItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper {
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "count", source = "count")
    @Mapping(target = "price", source = "price")
    OrderItem toOrderItem(OrderItemResponse orderItemResponse);
}