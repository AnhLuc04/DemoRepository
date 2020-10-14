package com.codegym.repository;

import com.codegym.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProductRepository  extends PagingAndSortingRepository<Product,Long> {
    Optional<Product> findById(Long id);
}
