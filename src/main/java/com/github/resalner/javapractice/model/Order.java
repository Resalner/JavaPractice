package com.github.resalner.javapractice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
@ToString(includeFieldNames = true)
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "status", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "comments")
    private String comments;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;
}