package com.github.resalner.javapractice.request;

import com.github.resalner.javapractice.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.valiadation.constraints.NotBlank;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest{
  @NotBlank(massage = "Необходимо указать имя")
  private String username;

  @NotBlank(massage = "Необходимо указать пароль")
  private String password;

  @NotBlank(massage = "Необходимо указать роль")
  private Role role;
}