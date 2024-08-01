package com.github.resalner.javapractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.resalner.javapractice.model.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
    @Modifying
    @Query(value = "DELETE FROM order_items WHERE id = ?1", nativeQuery = true)
    void deleteById(long id);
}
