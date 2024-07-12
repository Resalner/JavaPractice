package com.github.resalner.javapractice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.valiadation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {

    @NotBlank(message = "Необходимо указать номер заказа")
    private long orderId;

    @NotBlank(message = "Необходимо указать номер товара")
    private long productId;

    @NotBlank(message = "Необходимо указать количество")
    private Integer count;

    @NotBlank(message = "Необходимо указать цену")
    private Double price;

}