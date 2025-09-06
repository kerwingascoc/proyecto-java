package com.nttdata.dockerized.postgresql.service.impl;

import com.nttdata.dockerized.postgresql.mapper.CategoryMapper;
import com.nttdata.dockerized.postgresql.model.dto.CategoryDto;
import com.nttdata.dockerized.postgresql.model.dto.CategorySaveRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.CategorySaveResponseDto;
import com.nttdata.dockerized.postgresql.model.entity.Category;
import com.nttdata.dockerized.postgresql.repository.CategoryRepository;
import com.nttdata.dockerized.postgresql.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> listAll() {
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        return CategoryMapper.INSTANCE.map(categories);
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
        return CategoryMapper.INSTANCE.map(category);
    }

    @Override
    @Transactional
    public CategorySaveResponseDto save(CategorySaveRequestDto request) {
        if (request == null || request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío");
        }

        Category category = CategoryMapper.INSTANCE.toEntity(request);
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.INSTANCE.toCategorySaveResponseDto(savedCategory);
    }

    @Override
    @Transactional
    public CategoryDto update(Long id, CategorySaveRequestDto request) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));

        Category categoryToUpdate = CategoryMapper.INSTANCE.toEntityForUpdate(id, request, existingCategory);
        Category updatedCategory = categoryRepository.save(categoryToUpdate);
        return CategoryMapper.INSTANCE.map(updatedCategory);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Categoría no encontrada con ID: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
