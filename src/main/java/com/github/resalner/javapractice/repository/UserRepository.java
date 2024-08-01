package com.github.resalner.javapractice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.resalner.javapractice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	
    @Modifying
    @Query(value = "DELETE FROM users WHERE id = ?1", nativeQuery = true)
    void deleteById(long id);
}
