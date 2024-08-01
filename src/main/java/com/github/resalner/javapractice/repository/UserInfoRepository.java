package com.github.resalner.javapractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.resalner.javapractice.model.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long>{

    @Modifying
    @Query(value = "DELETE FROM users_info WHERE id = ?1", nativeQuery = true)
    void deleteById(long id);
}
