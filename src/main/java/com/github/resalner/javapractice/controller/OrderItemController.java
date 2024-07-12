package com.github.resalner.javapractice.controller;

import com.github.resalner.javapractice.model.OrderItem;
import com.github.resalner.javapractice.repository.OrderItemRepository;
import com.github.resalner.javapractice.request.OrderItemRequest;
import com.github.resalner.javapractice.service.OrderItemService;
import com.github.resalner.javapractice.dto.OrderItemResponse;
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

import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/order-items" )
@RequiredArgsConstructor
public class OrderItemController{

  private final OrderItemService orderItemService;
  
  @GetMapping
  public List<OrderItemResponse> getOrderItems(){
    return orderItemService.getOrderItems();
  } 
  
  @PostMapping
  public OrderItemResponse saveOrderItem(@RequestBody @Valid OrderItemRequest orderItemRequest){
    orderItemService.addOrderItem(orderItemRequest);
  }
  
  @GetMapping("/{id}")
  public OrderItemResponse getOrderItem(@PathVariable("id") long orderItemId){
    return orderItemService.getOrderItem(orderItemId);
  }
  
  @DeleteMapping("/{id}")
  public void deleteOrderItem(@PathVariable("id") long orderItemId){
    orderItemService.deleteOrderItem(orderItemId);
  }
  
  @PutMapping("/{id}")
  public OrderItemResponse updateOrderItem(@PathVariable("id") long orderItemId, @RequestBody @Valid OrderItemRequest orderItemRequest){
    return orderItemService.updateOrderItem(orderItemId, orderItemRequest);
  }
}