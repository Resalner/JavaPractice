package com.github.resalner.javapractice.controller;

import com.github.resalner.javapractice.model.Order;
import com.github.resalner.javapractice.repository.OrderRepository;
import com.github.resalner.javapractice.request.OrderRequest;
import com.github.resalner.javapractice.service.OrderService;
import com.github.resalner.javapractice.dto.OrderResponse;
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
@RequestMapping(path = "/api/v1/orders" )
@RequiredArgsConstructor
public class OrderController{
  
  private final OrderService orderService;
  
  @GetMapping
  public List<OrderResponse> getOrders(){
    return orderService.getOrders();
  } 
  
  @PostMapping
  public OrderResponse saveOrder(@RequestBody @Valid OrderRequest orderRequest){
    orderService.addOrder(orderRequest);
  }
  
  @GetMapping("/{id}")
  public OrderResponse getOrder(@PathVariable("id") long orderid){
    return orderService.getOrder(orderid);
  }
  
  @DeleteMapping("/{id}")
  public void deleteOrder(@PathVariable("id") long orderid){
    orderService.deleteOrder(orderid);
  }
  
  @PutMapping("/{id}")
  public OrderResponse updateOrder(@PathVariable("id") long orderid, @RequestBody @Valid OrderRequest orderRequest){
    return orderService.updateOrder(orderid, orderRequest);
  }
}