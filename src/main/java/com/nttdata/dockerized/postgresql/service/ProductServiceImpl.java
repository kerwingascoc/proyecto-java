package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.entity.Category;
import com.nttdata.dockerized.postgresql.model.entity.Product;
import com.nttdata.dockerized.postgresql.repository.CategoryRepository;
import com.nttdata.dockerized.postgresql.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> listAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public Product save(Product user) {
        return productRepository.save(user);
    }

    @Override
    public Product update(Product user, Long id) {

        return productRepository.findById(id).map(
                p ->{
                    p.setName(user.getName());
                    return productRepository.save(p);
                }).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findByPriceBetween(BigDecimal priceMin, BigDecimal priceMax) {
        return productRepository.findByPriceBetween(priceMin, priceMax);
    }

    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public Product assingProductToCategory(Long productId, Long categoryId) {

        Product product = productRepository.findById(productId).orElseThrow();
        Category category = categoryRepository.findById(categoryId).orElseThrow();

        product.setCategory(category);
        return productRepository.save(product);
    }


}
