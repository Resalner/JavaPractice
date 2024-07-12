package com.github.resalner.javapractice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
    private long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private Date birthDate;
    private boolean gender;
    private String email;
}