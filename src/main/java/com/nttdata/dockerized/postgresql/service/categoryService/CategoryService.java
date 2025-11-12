package com.nttdata.dockerized.postgresql.service.categoryService;

import com.nttdata.dockerized.postgresql.model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> listAll();

    Optional<Category> findById(Long id);

    Category save(Category category);

    void delete(Category category);

    Category update(Category category);
}