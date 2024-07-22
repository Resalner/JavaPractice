package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.model.Order;
import com.github.resalner.javapractice.repository.OrderItemRepository;
import com.github.resalner.javapractice.repository.OrderRepository;
import com.github.resalner.javapractice.repository.ProductRepository;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order saveOrder(Order order) {
        order = orderRepository.save(order);
        return order;
    }

    @Override
    public Order getOrder(long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден заказ с id = " + id));
    }

    @Override
    public void deleteOrder(long id) {
        orderRepository.deleteById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден заказ с id = " + id));
    }

    @Override
    public Order updateOrder(long id, Order orderForUpdate) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден заказ с id = " + id));
        if (Objects.nonNull(orderForUpdate.userId())
                && !"".equals(orderForUpdate.userId())) {

            order.setUserId(orderForUpdate.userId());
        }
        if (Objects.nonNull(orderForUpdate.orderDate())
                && !"".equals(orderForUpdate.orderDate())) {

            order.setOrderDate(orderForUpdate.orderDate());
        }
        if (Objects.nonNull(orderForUpdate.totalPrice())
                && !"".equals(orderForUpdate.totalPrice())) {

            order.setTotaPprice(orderForUpdate.totalPrice());
        }
        if (Objects.nonNull(orderForUpdate.status())
                && !"".equals(orderForUpdate.status())) {

            order.setStatus(orderForUpdate.status());
        }
        if (Objects.nonNull(orderForUpdate.adressId())
                && !"".equals(orderForUpdate.adressId())) {

            order.setAdressId(orderForUpdate.adressId());
        }
        if (Objects.nonNull(orderForUpdate.comments())
                && !"".equals(orderForUpdate.comments())) {

            order.setComments(orderForUpdate.comments());
        }
        order = orderRepository.save(order);
        return order;
    }
}