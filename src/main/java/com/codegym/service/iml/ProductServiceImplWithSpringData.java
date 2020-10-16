package com.codegym.service.iml;

import com.codegym.exception.DuplicateLastNameException;
import com.codegym.service.ProductService;
import com.codegym.model.Product;
import com.codegym.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class ProductServiceImplWithSpringData implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageInfo) throws Exception {
      return  productRepository.findAll(pageInfo);
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) throws Exception {
        return productRepository.findById(id);
    }



    @Override
    public Product save(Product product) throws DuplicateLastNameException {
//        return productRepository.save(product);
        try {
            return productRepository.save(product);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateLastNameException();
        }
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(id);
    }
}
