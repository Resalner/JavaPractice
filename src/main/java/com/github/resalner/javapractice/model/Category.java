package com.github.resalner.javapractice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category")
@Data
@ToString(includeFieldNames=true)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
}
