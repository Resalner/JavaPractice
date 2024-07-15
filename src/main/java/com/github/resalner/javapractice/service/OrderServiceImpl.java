package com.github.resalner.javapractice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.resalner.javapractice.exception.EntityNotFoundException;
import com.github.resalner.javapractice.model.Order;
import com.github.resalner.javapractice.repository.OrderRepository;
import com.github.resalner.javapractice.request.OrderRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public void saveOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setUserId(orderRequest.userId());
        order.setOrderDate(orderRequest.orderDate());
        order.setTotaPprice(orderRequest.totalPrice());
        order.setStatus(orderRequest.status());
        order.setAdressId(orderRequest.adressId());
        order.setComments(orderRequest.comments());
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

    public Order updateOrder(long id, OrderRequest orderRequest) {
        Order or = orderRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден заказ с id = " + id));
        if (Objects.nonNull(orderRequest.userId())
                && !"".equals(orderRequest.userId())) {

            or.setUserId(orderRequest.userId());

        }
        if (Objects.nonNull(orderRequest.orderDate())
                && !"".equals(orderRequest.orderDate())) {

            or.setOrderDate(orderRequest.orderDate());

        }
        if (Objects.nonNull(orderRequest.totalPrice())
                && !"".equals(orderRequest.totalPrice())) {

            or.setTotaPprice(orderRequest.totalPrice());

        }
        if (Objects.nonNull(orderRequest.status())
                && !"".equals(orderRequest.status())) {

            or.setStatus(orderRequest.status());

        }
        if (Objects.nonNull(orderRequest.adressId())
                && !"".equals(orderRequest.adressId())) {

            or.setAdressId(orderRequest.adressId());

        }
        if (Objects.nonNull(orderRequest.comments())
                && !"".equals(orderRequest.comments())) {

            or.setComments(orderRequest.comments());

        }

        orderRepository.save(or);
    }
}