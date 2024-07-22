package com.github.resalner.javapractice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.resalner.javapractice.exception.EntityNotFoundException;
import com.github.resalner.javapractice.model.OrderItem;
import com.github.resalner.javapractice.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implement OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> getOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem saveOrderItem(OrderItem orderItem) {
        orderItem = orderItemRepository.save(orderItem);
        return orderItem;
    }

    @Override
    public OrderItem getOrderItem(long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден элемент заказа с id = " + id));
    }

    @Override
    public void deleteOrderItem(long id) {
        orderItemRepository.deleteById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден элемент заказа с id = " + id));
    }

    @Override
    public OrderItem updateOrderItem(long id, OrderItem orderItemForUpdate) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден элемент заказа с id = " + id));
        if (Objects.nonNull(orderItemForUpdate.orderId())
                && !"".equals(orderItemForUpdate.orderId())) {

            orderItem.setOrderId(orderItemForUpdate.orderId());
        }
        if (Objects.nonNull(orderItemForUpdate.productId())
                && !"".equals(orderItemForUpdate.productId())) {

            orderItem.setProductId(orderItemForUpdate.productId());
        }
        if (Objects.nonNull(orderItemForUpdate.count())
                && !"".equals(orderItemForUpdate.count())) {

            orderItem.setCount(orderItemForUpdate.count());
        }
        if (Objects.nonNull(orderItemForUpdate.price())
                && !"".equals(orderItemForUpdate.price())) {

            orderItem.setPrice(orderItemForUpdate.price());
        }
        orderItem = orderItemRepository.save(orderItem);
        return orderItem;
    }
}