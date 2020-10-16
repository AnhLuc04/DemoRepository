package com.codegym.service;

import com.codegym.exception.DuplicateLastNameException;
import com.codegym.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Page<Product> findAll(Pageable pageInfo) throws Exception;
    List<Product> findAll();
    Optional<Product> findById(Long id) throws Exception;
    Product save(Product product) throws DuplicateLastNameException;
    void delete(Long id);
}
