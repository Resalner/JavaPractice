package com.github.resalner.javapractice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.valiadation.constraints.NotBlank;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest{
  
  @NotBlank(massage = "Необходимо указать город")
  private String city;

  @NotBlank(massage = "Необходимо указать улицу")
  private String street;

  @NotBlank(massage = "Необходимо указать номер дома")
  private String house_number;

  @NotBlank(massage = "Необходимо указать номер квартиры")
  private String apartament_number;
}