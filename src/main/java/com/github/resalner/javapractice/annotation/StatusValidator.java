package com.github.resalner.javapractice.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class StatusValidator implements ConstraintValidator<StatusAnnotation, String>{
  @Override
  public boolean isValid(String status, StatusAnnotation statusAnnotation){
    List<String> Statuses = Arrays.asList("PENDING",
                                            "SHIPPED",
                                            "DELIVERED",
                                            "CANCELED");
    return Statuses.contains(status);
  }
  
}