package com.github.resalner.javapractice.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class RoleValidator implements ConstraintValidator<RoleAnnotation, String>{
  @Override
  public boolean isValid(String role, RoleAnnotation roleAnnotation){
    List<String> roles = Arrays.asList("CUSTOMER",
                                            "ADMIN"
                                            );
    return roles.contains(role);
  }

}