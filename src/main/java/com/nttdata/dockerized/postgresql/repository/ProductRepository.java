package com.nttdata.dockerized.postgresql.repository;

import com.nttdata.dockerized.postgresql.model.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByPriceBetween(BigDecimal priceMin, BigDecimal priceMax);
    @Query("SELECT p.id, p.name, p.price FROM Product p JOIN p.category c WHERE c.id = ?1")
    List<Product> findByCategoryId(Long categoryId);
}
