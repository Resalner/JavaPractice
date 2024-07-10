package com.github.resalner.javapractice.request;

import com.github.resalner.javapractice.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.valiadation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest{
  
  @NotBlank(massage = "Необходимо указать пользователя")
  private long user_ID;
  
  @NotBlank(massage = "Необходимо указать дату")
  private Date orderDate;

  @NotBlank(massage = "Необходимо указать цену")
  private double totalPrice;  
  
  @NotBlank(massage = "Необходимо указать статус")
  private Status status;

  @NotBlank(massage = "Необходимо указать адрес")
  private long address_ID;  
  
  private String comments;
}