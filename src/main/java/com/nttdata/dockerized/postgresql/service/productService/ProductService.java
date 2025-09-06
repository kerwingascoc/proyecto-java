package com.nttdata.dockerized.postgresql.service.productService;

import com.nttdata.dockerized.postgresql.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    Product update(Product product);
    void deleteById(Long id);
    List<Product> findByCategoryName(String categoryName);
}
