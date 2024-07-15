package com.github.resalner.javapractice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.resalner.javapractice.exception.EntityNotFoundException;
import com.github.resalner.javapractice.model.OrderItem;
import com.github.resalner.javapractice.repository.OrderItemRepository;
import com.github.resalner.javapractice.request.OrderItemRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public List<OrderItem> getOrderItems() {
        return orderItemRepository.findAll();
    }

    public void saveOrderItem(OrderItemRequest orderItemRequest) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(orderItemRequest.orderId());
        orderItem.setProductId(orderItemRequest.productId());
        orderItem.setCount(orderItemRequest.count());
        orderItem.setPrice(orderItemRequest.price());
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

    public OrderItem updateOrderItem(long id, OrderItemRequest orderItemRequest) {
        OrderItem or = orderItemRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден элемент заказа с id = " + id));
        if (Objects.nonNull(orderItemRequest.orderId())
                && !"".equals(orderItemRequest.orderId())) {

            or.setOrderId(orderItemRequest.orderId());

        }
        if (Objects.nonNull(orderItemRequest.productId())
                && !"".equals(orderItemRequest.productId())) {

            or.setProductId(orderItemRequest.productId());

        }
        if (Objects.nonNull(orderItemRequest.count())
                && !"".equals(orderItemRequest.count())) {

            or.setCount(orderItemRequest.count());

        }
        if (Objects.nonNull(orderItemRequest.price())
                && !"".equals(orderItemRequest.price())) {

            or.setPrice(orderItemRequest.price());

        }

        orderItemRepository.save(or);
    }
}