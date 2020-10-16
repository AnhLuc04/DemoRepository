package com.codegym.service;


import com.codegym.exception.DuplicateLastNameException;
import com.codegym.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    Customer findById(Long id);

    Customer save(Customer customer) throws DuplicateLastNameException;

    void remove(Long id);
}
