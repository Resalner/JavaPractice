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

    public List<OrderItem> getOrderItems() {
        return orderItemRepository.findAll();
    }

    public void saveOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    public OrderItem getOrderItem(long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден элемент заказа с id = " + id));
    }

    public void deleteOrderItem(long id) {
        orderItemRepository.deleteById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден элемент заказа с id = " + id));
    }

    public OrderItem updateOrderItem(long id, OrderItem orderItem) {
        OrderItem or = orderItemRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден элемент заказа с id = " + id));
        if (Objects.nonNull(orderItem.orderId())
                && !"".equals(orderItem.orderId())) {

            or.setOrderId(orderItem.orderId());
        }
        if (Objects.nonNull(orderItem.productId())
                && !"".equals(orderItem.productId())) {

            or.setProductId(orderItem.productId());
        }
        if (Objects.nonNull(orderItem.count())
                && !"".equals(orderItem.count())) {

            or.setCount(orderItem.count());
        }
        if (Objects.nonNull(orderItem.price())
                && !"".equals(orderItem.price())) {

            or.setPrice(orderItem.price());
        }
        orderItemRepository.save(or);
        return or;
    }
}