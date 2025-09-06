package com.nttdata.dockerized.postgresql.controller.ProductController;

import com.nttdata.dockerized.postgresql.mapper.ProductMapper;
import com.nttdata.dockerized.postgresql.model.dto.ProductDTO.ProductDto;
import com.nttdata.dockerized.postgresql.model.dto.ProductDTO.ProductPatchDto;
import com.nttdata.dockerized.postgresql.model.dto.ProductDTO.ProductSaveRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.ProductDTO.ProductSaveResponseDto;
import com.nttdata.dockerized.postgresql.model.entity.Category;
import com.nttdata.dockerized.postgresql.model.entity.Product;
import com.nttdata.dockerized.postgresql.service.categoryService.CategoryService;
import com.nttdata.dockerized.postgresql.service.productService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.nttdata.dockerized.postgresql.mapper.ProductMapper.INSTANCE;


import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        List<ProductDto> body = INSTANCE.toDtoList(productService.findAll());
        return body.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(body);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Long id) {
        Optional<Product> opt = productService.findById(id);
        if (opt.isPresent()) {
            return ResponseEntity.ok(INSTANCE.toDto(opt.get()));
        }
        return ResponseEntity.notFound().build();
    }

    //el extra de buscar productos por categoria
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<ProductDto>> getByCategoryName(@PathVariable String categoryName) {
        List<Product> products = productService.findByCategoryName(categoryName);
        if (products.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(INSTANCE.toDtoList(products));
    }


    @PostMapping
    public ResponseEntity<ProductSaveResponseDto> save(@RequestBody ProductSaveRequestDto request) {
        if (request.getCategoryId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Category> catOpt = categoryService.findById(request.getCategoryId());
        if (catOpt.isEmpty()) return ResponseEntity.notFound().build();

        Product toSave = INSTANCE.toEntity(request);
        toSave.setCategory(catOpt.get());
        if (toSave.getActive() == null) toSave.setActive(Boolean.TRUE);

        Product saved = productService.save(toSave);
        ProductSaveResponseDto body = INSTANCE.toSaveResponseDto(saved);
        URI location = URI.create("/api/products/" + saved.getId());
        return ResponseEntity.created(location).body(body);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Product> opt = productService.findById(id);
        if (opt.isPresent()) {
            productService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody ProductDto dto) {
        Optional<Product> opt = productService.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        Product mapped = ProductMapper.INSTANCE.toEntity(dto);
        mapped.setId(id);


        if (dto.getCategoryId() != null) {
            Optional<Category> catOpt = categoryService.findById(dto.getCategoryId());
            if (catOpt.isEmpty()) return ResponseEntity.notFound().build();
            mapped.setCategory(catOpt.get());
        } else {
            mapped.setCategory(opt.get().getCategory());
        }

        Product updated = productService.update(mapped);
        return ResponseEntity.ok(INSTANCE.toDto(updated));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> patch(@PathVariable Long id, @RequestBody ProductPatchDto patch) {
        Optional<Product> opt = productService.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        Product existing = opt.get();
        INSTANCE.updateEntityFromPatchDto(patch, existing);

        if (patch.getCategoryId() != null) {
            Optional<Category> catOpt = categoryService.findById(patch.getCategoryId());
            if (catOpt.isEmpty()) return ResponseEntity.notFound().build();
            existing.setCategory(catOpt.get());
        }

        Product updated = productService.update(existing);
        return ResponseEntity.ok(INSTANCE.toDto(updated));
    }
}