package com.github.resalner.javapractice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.resalner.javapractice.model.OrderItem;
import com.github.resalner.javapractice.repository.OrderItemRepository;
import com.github.resalner.javapractice.request.OrderItemRequest;
import java.util.List;

@Service
public class OrderItemService{
  @Autowired
  private final OrderItemRepository orderItemRepository;

  public OrderItemService(OrderItemRepository orderItemRepository){
    this.orderItemRepository = orderItemRepository;
  }

  public List<OrderItem> getOrderItems(){
    return orderItemRepository.findAll();
  }

  public void addOrderItem(OrderItemRequest orderItemRequest){
    OrderItem orderItem = new OrderItem();
    orderItem.setOrder_ID(orderItemRequest.order_ID());
    orderItem.setProduct_ID(orderItemRequest.product_ID());
    orderItem.setCount(orderItemRequest.count());
    orderItem.setPrice(orderItemRequest.price());
    orderItemRepository.save(orderItem);
  }

  public OrderItem getOrderItem(long id){
    return orderItemRepository.findById(id).get();
  }

  public void deleteOrderItem(long id){
    orderItemRepository.deleteById(id);
  }
  public OrderItem updateOrderItem(long id, OrderItemRequest orderItemRequest){
    OrderItem or = orderItemRepository.findById(id).get();
    if(Objects.nonNull(orderItemRequest.order_ID()) && !"".equals(orderItemRequest.order_ID())){
      or.setOrder_ID(orderItemRequest.order_ID());
    }
    if(Objects.nonNull(orderItemRequest.product_ID()) && !"".equals(orderItemRequest.product_ID())){
      or.setProduct_ID(orderItemRequest.product_ID());
    }
    if(Objects.nonNull(orderItemRequest.count()) && !"".equals(orderItemRequest.count())){
      or.setCount(orderItemRequest.count());
    }
    if(Objects.nonNull(orderItemRequest.price()) && !"".equals(orderItemRequest.price())){
      or.setPrice(orderItemRequest.price());
    }
    orderItemRepository.save(or);
  }
}