package com.LeightonApp.MyThrift.dao;

import com.LeightonApp.MyThrift.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

// entity and integer primary key type
public interface StoreRepository extends JpaRepository<Store, Integer> {

    @Query("SELECT s FROM Store s JOIN s.user u WHERE u.username = :username AND u.password = :password")
    Optional<Store> authenticateUser(@Param("username") String username, @Param("password") String password);

    @Query("SELECT s FROM Store s JOIN s.user u WHERE u.username = :username")
    Optional<Store> findUser(@Param("username") String username);
}