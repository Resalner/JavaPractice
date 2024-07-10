package com.github.resalner.javapractice.service;

import org.springframework.stereotype.Service;
import com.github.resalner.javapractice.model.Order;
import com.github.resalner.javapractice.repository.OrderRepository;
import com.github.resalner.javapractice.request.OrderRequest;
import java.util.List;


@Service
public class OrderService{

  private final OrderRepository orderRepository;

  public OrderService(OrderRepository orderRepository){
    this.orderRepository = orderRepository;
  }

  public List<Order> getOrders(){
    return orderRepository.findAll();
  }

  public void addOrder(Order order){
    Order order = new Order();
    order.setUser_ID(orderRequest.user_ID());
    order.setOrder_date(orderRequest.order_date());
    order.setTotal_price(orderRequest.total_price());
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
    Order or = orderRepository.findById(id).get();
    if(Objects.nonNull(orderRequest.user_ID()) && !"".equals(orderRequest.user_ID())){
      or.setUser_ID(orderRequest.user_ID());
    }
    if(Objects.nonNull(orderRequest.order_date()) && !"".equals(orderRequest.order_date()){
      or.setOrder_date(orderRequest.order_date());
    }
    if(Objects.nonNull(orderRequest.total_price()) && !"".equals(orderRequest.total_price()){
      or.setTotal_price(orderRequest.total_price());
    }
    if(Objects.nonNull(orderRequest.status()) && !"".equals(orderRequest.status()){
      or.setStatus(orderRequest.status());
    }
    if(Objects.nonNull(orderRequest.adress_ID()) && !"".equals(orderRequest.adress_ID()){
      or.setAdress_ID(orderRequest.adress_ID());
    }
    if(Objects.nonNull(orderRequest.comments()) && !"".equals(orderRequest.comments()){
      or.setComments(orderRequest.comments());
    }
    orderRepository.save(or);
  }
}