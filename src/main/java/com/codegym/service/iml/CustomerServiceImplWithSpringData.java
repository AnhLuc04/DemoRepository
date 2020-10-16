package com.codegym.service.iml;

import com.codegym.exception.DuplicateLastNameException;
import com.codegym.service.CustomerService;
import com.codegym.model.Customer;
import com.codegym.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public class CustomerServiceImplWithSpringData implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public List<Customer> findAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public Customer save(Customer customer) throws DuplicateLastNameException {
//        return customerRepository.save(customer);
        try {
            return customerRepository.save(customer);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateLastNameException();
        }
    }

    @Override
    public void remove(Long id) {
        customerRepository.delete(id);
    }
}
