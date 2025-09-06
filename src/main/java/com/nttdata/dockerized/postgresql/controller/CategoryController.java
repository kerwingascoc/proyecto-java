package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.model.dto.*;
import com.nttdata.dockerized.postgresql.model.entity.Category;
import com.nttdata.dockerized.postgresql.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.nttdata.dockerized.postgresql.mapper.CategoryMapper.INSTANCE;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return INSTANCE.map(categoryService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(INSTANCE.map(category));
    }
    @PostMapping
    public ResponseEntity<CategorySaveResponseDto> saveCategory(@RequestBody CategorySaveRequestDto dto) {
        Category saved = categoryService.save(INSTANCE.toEntity(dto));
        return ResponseEntity.ok(INSTANCE.toCategorySaveResponseDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id,
                                                      @RequestBody CategoryUpdateRequestDto dto) {
        Category updated = categoryService.updateById(id, INSTANCE.toEntity(dto));
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(INSTANCE.map(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
