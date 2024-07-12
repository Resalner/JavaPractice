package com.github.resalner.javapractice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
  private long id;
  private long user_ID;
  private Date order_date;
  private double total_price;
  private String status;
  private long adress_ID;
  private String comments;
}