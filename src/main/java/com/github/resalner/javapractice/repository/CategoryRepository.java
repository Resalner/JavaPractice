package com.github.resalner.javapractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.resalner.javapractice.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
