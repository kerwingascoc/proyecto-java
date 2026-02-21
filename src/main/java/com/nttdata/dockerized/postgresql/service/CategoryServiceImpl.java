package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.dto.CategoryRequestDto;
import com.nttdata.dockerized.postgresql.model.entity.Category;
import com.nttdata.dockerized.postgresql.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> listAll() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    @Override
    public Category save(Category user) {
        return categoryRepository.save(user);
    }

    @Override
    public Category update(CategoryRequestDto user, Long id) {
        return categoryRepository.findById(id).map(category -> {
            category.setName(user.getName());
            return categoryRepository.save(category);
        }).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
