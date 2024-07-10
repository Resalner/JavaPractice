package com.github.resalner.javapractice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.valiadation.constraints.NotBlank;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest{
  @NotBlank(message = "Не заполнено поле имя")
  private String name;

  @NotBlank(message = "Не заполнено поле описание")
  private String description;
  
  @NotBlank(message = "Не заполнено поле цена")
  private Double price;
  
  @NotBlank(message = "Не заполнено поле категория")
  private long category_ID;
}