package com.github.resalner.javapractice.model;

import java.util.Set;

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

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Product> products;
}