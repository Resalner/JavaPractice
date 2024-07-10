package com.github.resalner.javapractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import jakarta.validation.Valid;
import com.github.resalner.javapractice.model.Order;
import com.github.resalner.javapractice.repository.OrderRepository;
import com.github.resalner.javapractice.request.OrderRequest;

import com.github.resalner.javapractice.service.OrderService;
import java.util.List;


@RestController
@RequestMapping(path = "/api/orders" )
public class OrderController{

  private final OrderService orderService;
  public OrderController(OrderService orderService){
    this.orderService = orderService;
  }
// получение списка заказов
  @GetMapping
  public List<Order> getOrders(){
    return orderService.getOrders();
  } 
// добавление нового заказа
 @PostMapping("/addOrder")
  public void saveOrder(@RequestBody @Valid OrderRequest orderRequest){
    orderService.addOrder(orderRequest);
  }
  @GetMapping("/getOreder/{id}")
  public Order getOrder(@PathVariable("id") long orderid){
    return orderService.getOrder(orderid);
  }
  @DeleteMapping("/deleteOrder/{id}")
  public void deleteOrder(@PathVariable("id") long orderid){
    orderService.deleteOrder(orderid);
  }
  @PostMapping("/updateOrder/{id}")
  public Order updateOrder(@PathVariable("id") long orderid, @RequestBody @Valid OrderRequest orderRequest){
    return orderService.updateOrder(orderid, orderRequest);
  }
}