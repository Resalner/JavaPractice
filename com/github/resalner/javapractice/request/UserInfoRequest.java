package com.github.resalner.javapractice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.valiadation.constraints.NotBlank;
import javax.valiadation.constraints.Email;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRequest{
  @NotBlank(massage = "Необходимо указать имя")
  private String name;

  @NotBlank(massage = "Необходимо указать фамилию")
  private String surname; 
  
  @NotBlank(massage = "Необходимо указать номер телефона")
  private String phonenumber;

  @NotBlank(massage = "Необходимо указать дату рождения")
  private Date birthDate;

  @NotBlank(massage = "Необходимо указать пол")
  private boolean gender;
  
  @NotBlank(massage = "Необходимо указать email")
  @Email(message = "Необходимо указать корректный email")
  private String email;
}