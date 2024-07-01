package com.LeightonApp.MyThrift.service;

import com.LeightonApp.MyThrift.dao.CustomerRepository;
import com.LeightonApp.MyThrift.entity.Customer;
import com.LeightonApp.MyThrift.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository theCustomerRepository) {
        customerRepository = theCustomerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(int userID) {
        return customerRepository.findById(userID);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteById(int userID) {
        customerRepository.deleteById(userID);
    }

    @Override
    public Optional<Customer> findByUsername(String customerUsername)
    {
        return customerRepository.findByUsername(customerUsername);
    }
}
