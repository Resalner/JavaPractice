package com.github.resalner.javapractice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse{
  private long id;
  private long product_ID;
  private long order_ID;
  private Integer count;
  private double price;
}