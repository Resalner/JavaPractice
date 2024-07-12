package com.github.resalner.javapractice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.valiadation.constraints.NotBlank;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest{
  
  @NotBlank(message = "Необходимо указать город")
  private String city;

  @NotBlank(message = "Необходимо указать улицу")
  private String street;

  @NotBlank(message = "Необходимо указать номер дома")
  private String house_number;

  private String apartament_number;
}
