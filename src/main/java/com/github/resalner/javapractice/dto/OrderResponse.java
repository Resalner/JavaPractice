package com.github.resalner.javapractice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private long id;
    private long userId;
    private Date orderDate;
    private double totalPrice;
    private String status;
    private long adressId;
    private String comments;
}