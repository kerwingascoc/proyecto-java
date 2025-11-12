package com.nttdata.dockerized.postgresql.controller.CategoryController;


import com.nttdata.dockerized.postgresql.mapper.CategoryMapper;
import com.nttdata.dockerized.postgresql.model.dto.CategoryDTO.CategoryDto;
import com.nttdata.dockerized.postgresql.model.dto.CategoryDTO.CategoryPatchDto;
import com.nttdata.dockerized.postgresql.model.dto.CategoryDTO.CategorySaveRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.CategoryDTO.CategorySaveResponseDto;
import com.nttdata.dockerized.postgresql.model.entity.Category;
import com.nttdata.dockerized.postgresql.service.categoryService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static com.nttdata.dockerized.postgresql.mapper.CategoryMapper.INSTANCE;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> body = INSTANCE.map(categoryService.listAll());
        return ResponseEntity.ok(body);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        Optional<Category> opt = categoryService.findById(id);
        if (opt.isPresent()) {
            return ResponseEntity.ok(INSTANCE.map(opt.get()));
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<CategorySaveResponseDto> save(@RequestBody CategorySaveRequestDto request) {
        Category toSave = INSTANCE.toEntity(request);
        Category saved = categoryService.save(toSave);
        CategorySaveResponseDto body = INSTANCE.toCategorySaveResponseDto(saved);
        URI location = URI.create("/api/category/" + saved.getId());
        return ResponseEntity.created(location).body(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        Optional<Category> opt = categoryService.findById(id);
        if (opt.isPresent()) {
            categoryService.delete(opt.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody CategoryDto dto) {
        Optional<Category> opt = categoryService.findById(id);
        if (opt.isPresent()) {
            Category mapped = CategoryMapper.INSTANCE.map(dto);

            mapped.setId(id);
            Category updated = categoryService.update(mapped);
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Category> patchCategory(@PathVariable Long id, @RequestBody CategoryPatchDto patchDto) {
        Optional<Category> opt = categoryService.findById(id);
        if (opt.isPresent()) {
            Category existing = opt.get();
            INSTANCE.updateCategoryFromPatchDto(patchDto, existing);
            Category updated = categoryService.update(existing);
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }
}