package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.model.entity.Product;
import com.nttdata.dockerized.postgresql.repository.ProductRepository;
import com.nttdata.dockerized.postgresql.service.ProductService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product){
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable Long id){
        return ResponseEntity.ok(productService.update(product,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok(productService.listAll());
    }

    @GetMapping("/by-price")
    public ResponseEntity<List<Product>> findByPriceBetween(@RequestParam BigDecimal priceMin, @RequestParam BigDecimal priceMax){
        return ResponseEntity.ok(productService.findByPriceBetween(priceMin, priceMax));
    }

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<Product>> findByCategoryId(@PathVariable Long categoryId){
        return ResponseEntity.ok(productService.findByCategoryId(categoryId));
    }

    @PatchMapping("/{productId}/category/{categoryId}")
    public ResponseEntity<Product> assignProductToCategory(@PathVariable Long productId, @PathVariable Long categoryId){
        return ResponseEntity.ok(productService.assingProductToCategory(productId,categoryId));
    }
}
