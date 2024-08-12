package com.github.resalner.javapractice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.resalner.javapractice.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByUserId(Long userId);

	@Modifying
	@Query(value = "DELETE FROM orders WHERE id = :id", nativeQuery = true)
	void deleteById(long id);
}
