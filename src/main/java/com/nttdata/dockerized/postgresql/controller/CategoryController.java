package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.model.dto.CategoryDto;
import com.nttdata.dockerized.postgresql.model.dto.CategoryRequestDto;
import com.nttdata.dockerized.postgresql.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.nttdata.dockerized.postgresql.mapper.CategoryMapper.CATEGORY_MAPPER;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll(){
        return ResponseEntity.ok(CATEGORY_MAPPER.map(categoryService.listAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(CATEGORY_MAPPER.map(categoryService.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@RequestBody CategoryRequestDto categoryRequestDto, @PathVariable Long id){
        return ResponseEntity.ok(CATEGORY_MAPPER.map(categoryService.update(categoryRequestDto,id)));
    }

    @PostMapping()
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryRequestDto categoryRequestDto){
        return ResponseEntity.ok(CATEGORY_MAPPER.map(categoryService.save(CATEGORY_MAPPER.toEntity(categoryRequestDto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDto> delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
