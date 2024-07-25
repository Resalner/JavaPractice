package com.github.resalner.javapractice.controller;

import com.github.resalner.javapractice.model.OrderItem;
import com.github.resalner.javapractice.repository.OrderItemRepository;
import com.github.resalner.javapractice.request.OrderItemRequest;
import com.github.resalner.javapractice.service.OrderItemService;
import com.github.resalner.javapractice.dto.OrderItemResponse;
import com.github.resalner.javapractice.map.OrderItemMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.mapstruct.factory.Mappers;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/order-items")
@RequiredArgsConstructor
public class OrderItemController {

    @Autowired
    privatefinal OrderItemMapper
    mapper;
    private final OrderItemService orderItemService;

    @GetMapping
    public List<OrderItemResponse> getOrderItems() {
        List<OrderItem> orderItems = orderItemService.getOrderItems();
        return mapper.toDomain(orderItems);
    }

    @PostMapping
    public OrderItemResponse saveOrderItem(@RequestBody @Valid OrderItemRequest orderItemRequest) {
        OrderItem orderItem = mapper.toOrderItem(orderItemRequest);
        orderItemService.addOrderItem(orderItem);
        return mappers.toResponse(orderItem);
    }

    @GetMapping("/{id}")
    public OrderItemResponse getOrderItem(@PathVariable("id") long orderItemId) {
        OrderItem orderItem = orderItemService.getOrderItem(orderItemId);
        return mapper.toResponse(orderItem);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderItem(@PathVariable("id") long orderItemId) {
        orderItemService.deleteOrderItem(orderItemId);
    }

    @PutMapping("/{id}")
    public OrderItemResponse updateOrderItem(@PathVariable("id") long orderItemId, @RequestBody @Valid OrderItemRequest orderItemRequest) {
        OrderItem orderItem = mapper.toOrderItem(orderItemRequest);
        orderItem = orderItemService.updateOrderItem(orderItemId, orderItem);
        return mapper.toResponse(orderItem);
    }
}