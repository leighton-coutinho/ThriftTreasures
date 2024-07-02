package com.LeightonApp.MyThrift.service;

import com.LeightonApp.MyThrift.entity.Customer;
import com.LeightonApp.MyThrift.entity.Store;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> findAll();
    Optional<Customer> findById(int userID);
    Customer save(Customer customer);
    void deleteById(int userID);

    Optional<Customer> findByUsername(String customerUsername);

    Optional<Customer> authenticateUser(String username, String password);


}
