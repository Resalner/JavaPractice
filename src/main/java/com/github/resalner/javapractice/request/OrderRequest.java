package com.github.resalner.javapractice.request;

import com.github.resalner.javapractice.model.Status;
import com.github.resalner.javapractice.model.User;

import java.util.Date;

import com.github.resalner.javapractice.annotation.StatusAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    @NotBlank(message = "Необходимо указать пользователя")
    private long userId;

    @NotBlank(message = "Необходимо указать дату")
    private Date orderDate;

    @NotBlank(message = "Необходимо указать цену")
    private double totalPrice;

    @NotBlank(message = "Необходимо указать статус")
    @StatusAnnotation
    private Status status;

    @NotBlank(message = "Необходимо указать адрес")
    private long addressId;

    private String comments;
}