package com.github.resalner.javapractice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDataResponse {
    private Long id;
    private String username;
    private String role;
    private String name;
    private String surname;
    private String phoneNumber;
    private Date birthDate;
    private boolean gender;
    private String email;
}
