package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.model.Order;
import com.github.resalner.javapractice.request.OrderRequest;

import java.util.List;

public interface OrderService {
    List<Order> getOrders();

    void saveOrder(OrderRequest orderRequest);

    Order getOrder(long id);

    void deleteOrder(long id);

    Order updateOrder(long id, OrderRequest orderRequest);
}