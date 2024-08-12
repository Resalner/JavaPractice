package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.model.Order;
import com.github.resalner.javapractice.model.OrderItem;

import java.util.List;

public interface OrderService {
    List<Order> getOrders();

    Order saveOrder(Order order);

    Order getOrder(long id);

    List<OrderItem> getOrderItemByOrderId(long orderId);

    void deleteOrder(long id);

    Order updateOrder(long id, Order order);

}