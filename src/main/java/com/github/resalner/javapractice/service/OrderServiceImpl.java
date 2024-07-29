package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.exception.EntityNotFoundException;
import com.github.resalner.javapractice.model.Address;
import com.github.resalner.javapractice.model.Order;
import com.github.resalner.javapractice.model.OrderItem;
import com.github.resalner.javapractice.model.Status;
import com.github.resalner.javapractice.model.User;
import com.github.resalner.javapractice.repository.OrderItemRepository;
import com.github.resalner.javapractice.repository.OrderRepository;
import com.github.resalner.javapractice.repository.ProductRepository;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

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
    List<OrderItem> getOrderItemByOrderId(long orderId) {
        return orderItemRepository.findAllById(orderId);
    }

    @Override
    public Order getOrder(long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("не найден заказ с id = " + id));
    }

    @Override
    public void deleteOrder(long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order updateOrder(long id, Order orderForUpdate) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("не найден заказ с id = " + id));

        User newUser = orderForUpdate.getUser();
        Date newOrderDate = orderForUpdate.getOrderDate();
        Double newTotalPrice = orderForUpdate.getTotalPrice();
        Status newStatus = orderForUpdate.getStatus();
        Address newAddress = orderForUpdate.getAddress();
        String newComments = orderForUpdate.getComments();

        if (Objects.nonNull(newUser)) {

            order.setUser(newUser);
        }
        if (Objects.nonNull(newOrderDate)) {

            order.setOrderDate(newOrderDate);
        }
        if (Objects.nonNull(newTotalPrice)) {

            order.setTotalPrice(newTotalPrice);
        }
        if (Objects.nonNull(newStatus)) {

            order.setStatus(newStatus);
        }
        if (Objects.nonNull(newAddress)) {

            order.setAddress(newAddress);
        }
        if (Objects.nonNull(newComments) && !"".equals(newComments)) {

            order.setComments(newComments);
        }
        order = orderRepository.save(order);
        return order;
    }
}