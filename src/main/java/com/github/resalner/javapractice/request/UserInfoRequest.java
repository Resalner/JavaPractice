package com.github.resalner.javapractice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.valiadation.constraints.Past;
import javax.valiadation.constraints.NotBlank;
import javax.valiadation.constraints.Email;
import javax.valiadation.constraints.Pattern;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRequest{
  @NotBlank(message = "Необходимо указать имя")
  @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message ="Имя может содержать только буквы")
  private String name;

  @NotBlank(message = "Необходимо указать фамилию")
  @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message ="Имя может содержать только буквы")
  private String surname; 
  
  @NotBlank(message = "Необходимо указать номер телефона")
  @Pattern(regexp = "^375(33|44|29|25)\\d{7}$", message ="неверный формат номера телефона"")
  private String phone_number;

  @NotBlank(message = "Необходимо указать дату рождения")
  @Past(message = "Дата рождения должна быть в прошлом")
  private Date birthDate;

  @NotBlank(message = "Необходимо указать пол")
  private boolean gender;
  
  @NotBlank(message = "Необходимо указать email")
  @Email(message = "Необходимо указать корректный email")
  private String email;
}