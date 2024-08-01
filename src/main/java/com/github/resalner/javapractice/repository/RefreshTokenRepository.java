package com.github.resalner.javapractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.resalner.javapractice.model.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	RefreshToken findByToken(String token);
	
    @Modifying
    @Query(value = "DELETE FROM refresh_tokens WHERE id = ?1", nativeQuery = true)
    void deleteById(long id);
}
