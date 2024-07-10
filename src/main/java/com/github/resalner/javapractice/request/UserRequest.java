package com.github.resalner.javapractice.request;

import com.github.resalner.javapractice.model.Role;
import com.github.resalner.javapractice.annotation.RoleAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.valiadation.constraints.NotBlank;
import javax.valiadation.constraints.Pattern;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest{
  @NotBlank(message = "Необходимо указать имя")
  @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message ="Имя может содержать только буквы")
  private String username;

  @NotBlank(message = "Необходимо указать пароль")
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])", messeg = "Пароль должен содержать хотя бы одну букву в верхнем и нижнем регистре и одну цифру")
  private String password;

  @NotBlank(message = "Необходимо указать роль")
  @RoleAnnotation
  private Role role;
}