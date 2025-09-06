package com.nttdata.dockerized.postgresql.service;


import com.nttdata.dockerized.postgresql.model.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    public List<Product> listAll();

    public Product findById(Long id);

    public Product save(Product user);

    public Product update(Product user, Long id);
    public void delete(Long id);
    List<Product> findByPriceBetween(BigDecimal priceMin, BigDecimal priceMax);
    List<Product> findByCategoryId(Long categoryId);

    Product assingProductToCategory(Long productId, Long categoryId);
}
