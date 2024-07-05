package com.github.resalner.javapractice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@Data
@ToString(includeFieldNames=true)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "house_number", nullable = false)
    private String house_number;

    @Column(name = "apartment_number")
    private String apartment_number;
}
