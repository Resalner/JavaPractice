package com.github.resalner.javapractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.resalner.javapractice.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	@Modifying
	@Query(value = "DELETE FROM address WHERE id = :id", nativeQuery = true)
	void deleteById(long id);
}
