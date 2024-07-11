package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.model.OrderItem;
import com.github.resalner.javapractice.request.OrderItemRequest;
import java.util.List;

public interface OrderItemServiceInterface {
    List<OrderItem> getOrderItems();
    void addOrderItem(OrderItemRequest orderItemRequest);
    void deleteOrderItem(long id);
    OrderItem getOrderItem(long id);
    OrderItem updateOrderItem(long id, OrderItemRequest orderItemRequest);
}