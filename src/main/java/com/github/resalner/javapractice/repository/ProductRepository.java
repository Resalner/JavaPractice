package com.github.resalner.javapractice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.resalner.javapractice.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	List<Product> findByCategoryId(Long categoryId);
}
