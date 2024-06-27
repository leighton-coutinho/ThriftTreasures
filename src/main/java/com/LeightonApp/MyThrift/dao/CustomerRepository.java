package com.LeightonApp.MyThrift.dao;

import com.LeightonApp.MyThrift.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

// entity and integer primary key type
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
