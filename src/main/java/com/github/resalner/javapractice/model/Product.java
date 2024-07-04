package com.github.resalner.javapractice.model;


import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 1024)
    private String description;

    @Column(name = "price")
    private double price;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;
}
