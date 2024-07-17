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
public class OrderServiceImpl implement OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public Order getOrder(long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден заказ с id = " + id));
    }

    public void deleteOrder(long id) {
        orderRepository.deleteById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден заказ с id = " + id));
    }


    public Order updateOrder(long id, Order order) {
        Order or = orderRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден заказ с id = " + id));
        if (Objects.nonNull(order.userId())
                && !"".equals(order.userId())) {

            or.setUserId(order.userId());
        }
        if (Objects.nonNull(order.orderDate())
                && !"".equals(order.orderDate())) {

            or.setOrderDate(order.orderDate());
        }
        if (Objects.nonNull(order.totalPrice())
                && !"".equals(order.totalPrice())) {

            or.setTotaPprice(order.totalPrice());
        }
        if (Objects.nonNull(order.status())
                && !"".equals(order.status())) {

            or.setStatus(order.status());
        }
        if (Objects.nonNull(order.adressId())
                && !"".equals(order.adressId())) {

            or.setAdressId(order.adressId());
        }
        if (Objects.nonNull(order.comments())
                && !"".equals(order.comments())) {

            or.setComments(order.comments());
        }
        orderRepository.save(or);
        return or;
    }
}