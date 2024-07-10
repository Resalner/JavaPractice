package com.github.resalner.javapractice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.resalner.javapractice.model.Order;
import com.github.resalner.javapractice.repository.OrderRepository;
import com.github.resalner.javapractice.request.OrderRequest;
import java.util.List;


@Service
public class OrderService{
  @Autowired
  private final OrderRepository orderRepository;

  public OrderService(OrderRepository orderRepository){
    this.orderRepository = orderRepository;
  }

  public List<Order> getOrders(){
    return orderRepository.findAll();
  }

  public void addOrder(OrderRequest orderRequest){
    Order order = new Order();
    order.setUser_ID(orderRequest.user_ID());
    order.setOrder_date(orderRequest.orderDate());
    order.setTotal_price(orderRequest.totalPrice());
    order.setStatus(orderRequest.status());
    order.setAdress_ID(orderRequest.adress_ID());
    order.setComments(orderRequest.comments());
    orderRepository.save(order);
  }

  public Order getOrder(long id){
    return orderRepository.findById(id).get();
  }
  
  public void deleteOrder(long id){
    orderRepository.deleteById(id);
  }
  public Order updateOrder(long id, OrderRequest orderRequest){
    Order or = orderRepository.findById(id).get().orElseThrow(() -> new RuntimeException("Order not found"));
    if(Objects.nonNull(orderRequest.user_ID()) && !"".equals(orderRequest.user_ID())){
      or.setUser_ID(orderRequest.user_ID());
    }
    if(Objects.nonNull(orderRequest.orderDate()) && !"".equals(orderRequest.orderDate())){
      or.setOrder_date(orderRequest.orderDate());
    }
    if(Objects.nonNull(orderRequest.totalPrice()) && !"".equals(orderRequest.totalPrice())){
      or.setTotal_price(orderRequest.totalPrice());
    }
    if(Objects.nonNull(orderRequest.status()) && !"".equals(orderRequest.status())){
      or.setStatus(orderRequest.status());
    }
    if(Objects.nonNull(orderRequest.adress_ID()) && !"".equals(orderRequest.adress_ID())){
      or.setAdress_ID(orderRequest.adress_ID());
    }
    if(Objects.nonNull(orderRequest.comments()) && !"".equals(orderRequest.comments())){
      or.setComments(orderRequest.comments());
    }
    orderRepository.save(or);
  }
}