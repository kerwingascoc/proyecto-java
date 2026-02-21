package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> listAll();
    Product findById(Long id);
    Product save(Product product);
    Product updateById(Long id, Product product);
    void deleteById(Long id);
}
