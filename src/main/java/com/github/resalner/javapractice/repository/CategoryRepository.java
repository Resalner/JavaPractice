package com.github.resalner.javapractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.resalner.javapractice.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    @Modifying
    @Query(value = "DELETE FROM category WHERE id = ?1", nativeQuery = true)
    void deleteById(long id);
}
