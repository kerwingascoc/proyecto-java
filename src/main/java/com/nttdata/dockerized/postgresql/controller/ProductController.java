package com.nttdata.dockerized.postgresql.controller;


import com.nttdata.dockerized.postgresql.model.dto.ProductDto;
import com.nttdata.dockerized.postgresql.model.dto.ProductSaveRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.ProductSaveResponseDto;
import com.nttdata.dockerized.postgresql.model.dto.ProductUpdateRequestDto;
import com.nttdata.dockerized.postgresql.model.entity.Category;
import com.nttdata.dockerized.postgresql.model.entity.Product;
import com.nttdata.dockerized.postgresql.service.CategoryService;
import com.nttdata.dockerized.postgresql.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.nttdata.dockerized.postgresql.mapper.ProductMapper.INSTANCE;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public List<ProductDto> getAllProducts() {
        return INSTANCE.map(productService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(INSTANCE.map(product));
    }

    @PostMapping
    public ResponseEntity<ProductSaveResponseDto> saveProduct(@RequestBody ProductSaveRequestDto dto) {
        Category category = categoryService.findById(dto.getCategoryId());
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }

        Product product = INSTANCE.toEntity(dto);
        product.setCategory(category);

        Product saved = productService.save(product);
        return ResponseEntity.ok(INSTANCE.toProductSaveResponseDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,
                                                    @RequestBody ProductUpdateRequestDto dto) {
        Product product = INSTANCE.toEntity(dto);
        Category category = categoryService.findById(dto.getCategoryId());
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }
        product.setCategory(category);

        Product updated = productService.updateById(id, product);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(INSTANCE.map(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
