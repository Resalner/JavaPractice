package com.github.resalner.javapractice.controller;

import com.github.resalner.javapractice.model.Order;
import com.github.resalner.javapractice.model.OrderItem;
import com.github.resalner.javapractice.repository.OrderRepository;
import com.github.resalner.javapractice.request.OrderRequest;
import com.github.resalner.javapractice.service.OrderService;
import com.github.resalner.javapractice.service.OrderItemService;
import com.github.resalner.javapractice.dto.OrderResponse;
import com.github.resalner.javapractice.dto.OrderItemResponse;
import com.github.resalner.javapractice.map.OrderMapper;
import com.github.resalner.javapractice.map.OrderItemMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private final OrderMapper mapper;
    private final OrderService orderService;
    private final OrderItemMapper map;

    @GetMapping
    public List<OrderResponse> getOrders() {
        List<Order> orders = orderService.getOrders();
        return mapper.toDomain(orders);
    }

    @GetMapping("/{id}")
    public List<OrderItemResponse> getOrderItems(@PathVariable("id") long orderId) {
        List<OrderItem> orderItems = orderItemService.getOrderItemByOrderId(orderId);
        return map.toDomain(orderItems);
    }

    @PostMapping
    public OrderResponse saveOrder(@RequestBody @Valid OrderRequest orderRequest) {
        Order order = mapper.toOrder(orderRequest);
        return mapper.toResponse(orderService.saveOrder(order));
    }

    @GetMapping("/{id}")
    public OrderResponse getOrder(@PathVariable("id") long orderid) {
        return mapper.toResponse(orderService.getOrder(orderid));
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") long orderid) {
        orderService.deleteOrder(orderid);
    }

    @PutMapping("/{id}")
    public OrderResponse updateOrder(@PathVariable("id") long orderid, @RequestBody @Valid OrderRequest orderRequest) {
        Order order = mapper.toOrder(orderRequest);
        order = orderService.updateOrder(orderid, order);
        return mapper.toResponse(order);
    }
}