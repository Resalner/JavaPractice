package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.model.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> getOrderItems();

    OrderItem saveOrderItem(OrderItem orderItem);

    void deleteOrderItem(long id);

    OrderItem getOrderItem(long id);

    OrderItem updateOrderItem(long id, OrderItem orderItem);

}