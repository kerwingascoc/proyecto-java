package com.nttdata.dockerized.postgresql.repository.ProductRepository;


import com.nttdata.dockerized.postgresql.model.entity.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

interface SpringDataProductJpa extends JpaRepository<Product, Long> {

    @Query(value = """
  SELECT p.id, p.name, p.price, p.active, p.category_id
  FROM products p
  JOIN categories c ON p.category_id = c.id
  WHERE c.name = :categoryName
  """, nativeQuery = true)
    List<Product> findByCategoryNameNative(String categoryName);
}

@Repository
@Primary
@Transactional
public class ProductRepositoryJpaImpl implements ProductRepository {

    private final SpringDataProductJpa jpa;

    public ProductRepositoryJpaImpl(SpringDataProductJpa jpa) {
        this.jpa = jpa;
    }

    @Override
    public Product save(Product product) {
        return jpa.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpa.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return jpa.findAll();
    }

    @Override
    public Product update(Product product) {
        return jpa.save(product);
    }

    @Override
    public void deleteById(Long id) {
        jpa.deleteById(id);
    }

    @Override
    public List<Product> findByCategoryName(String categoryName) {
        return jpa.findByCategoryNameNative(categoryName);
    }
}