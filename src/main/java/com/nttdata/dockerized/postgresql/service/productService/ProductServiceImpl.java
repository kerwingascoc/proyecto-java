package com.nttdata.dockerized.postgresql.service.productService;

import com.nttdata.dockerized.postgresql.model.entity.Product;
import com.nttdata.dockerized.postgresql.repository.ProductRepository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product update(Product product) {
        return productRepository.update(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findByCategoryName(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }
}