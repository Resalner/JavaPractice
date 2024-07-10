package com.github.resalner.javapractice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.valiadation.constraints.NotBlank;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest{
  @NotBlank(massage = "Необходимо указать название")
  private String name;
}