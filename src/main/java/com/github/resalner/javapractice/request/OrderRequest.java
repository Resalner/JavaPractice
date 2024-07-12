package com.github.resalner.javapractice.request;

import com.github.resalner.javapractice.model.Status;
import com.github.resalner.javapractice.model.User;
import com.github.resalner.javapractice.annotation.StatusAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.valiadation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    @NotBlank(message = "Необходимо указать пользователя")
    private long user_ID;

    @NotBlank(message = "Необходимо указать дату")
    private Date orderDate;

    @NotBlank(message = "Необходимо указать цену")
    private double totalPrice;

    @NotBlank(message = "Необходимо указать статус")
    @StatusAnnotation
    private Status status;

    @NotBlank(message = "Необходимо указать адрес")
    private long address_ID;

    private String comments;
}