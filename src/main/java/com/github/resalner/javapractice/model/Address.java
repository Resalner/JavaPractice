package com.github.resalner.javapractice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "address")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private String house_number;

    @Column(name = "apartment_number")
    private String apartment_number;
}
