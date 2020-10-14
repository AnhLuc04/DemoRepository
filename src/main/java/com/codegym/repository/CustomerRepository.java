package com.codegym.repository;

import com.codegym.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
    Optional<Customer> findById(Long id);

    void deleteById(Long id);

}
