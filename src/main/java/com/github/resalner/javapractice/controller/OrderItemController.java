package com.github.resalner.javapractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import jakarta.validation.Valid;
import com.github.resalner.javapractice.model.OrderItem;
import com.github.resalner.javapractice.repository.OrderItemRepository;
import com.github.resalner.javapractice.request.OrderItemRequest;

import com.github.resalner.javapractice.service.OrderItemService;
import java.util.List;

@RestController
@RequestMapping(path = "/api/orderitems" )
public class OrderItemController{
  private final OrderItemService orderItemService;
  public OrderItemController(OrederItemService orderItemService){
    this.orderItemService = orderItemService;
  }
// получение списка заказов
  @GetMapping
  public List<OrderItem> getOrderItems(){
    return orderItemService.getOrderItems();
  } 
// добавление нового заказа
  @PostMapping("/addOrderItem")
  public void saveOrderItem(@RequestBody @Valid OrderItemRequest orderItemRequest){
    orderItemService.addOrderItem(orderItemRequest);
  }
  @GetMapping("/getOrderItem/{id}")
  public OrderItem getOrderItem(@PathVariable("id") long orderItemId){
    return orderItemService.getOrderItem(orderItemId);
  }
  @DeleteMapping("/deleteOrderItem/{id}")
  public void deleteOrderItem(@PathVariable("id") long orderItemId){
    orderItemService.deleteOrderItem(orderItemId);
  }
  @PostMapping("/updateOrderItem/{id}")
  public OrderItem updateOrderItem(@PathVariable("id") long orderItemId, @RequestBody @Valid OrderItemRequest orderItemRequest){
    return orderItemService.updateOrderItem(orderItemId, orderItemRequest);
  }
}