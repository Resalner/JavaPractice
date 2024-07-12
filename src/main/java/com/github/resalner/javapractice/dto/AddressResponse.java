package com.github.resalner.javapractice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse{
  private long id;
  private String city;
  private String street;
  private String house_number;
  private String apartment_number;
}