package com.LeightonApp.MyThrift.dao;

import com.LeightonApp.MyThrift.entity.Customer;
import com.LeightonApp.MyThrift.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

// entity and integer primary key type
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("SELECT c FROM Customer c JOIN c.user u WHERE u.username = :username")
    Optional<Customer> findByUsername(@Param("username") String username);

    @Query("SELECT c FROM Customer c JOIN c.user u WHERE u.username = :username AND u.password = :password")
    Optional<Customer> authenticateUser(@Param("username") String username, @Param("password") String password);


}
