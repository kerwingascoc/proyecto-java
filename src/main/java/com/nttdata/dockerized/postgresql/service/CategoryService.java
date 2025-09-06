package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> listAll();

    Category findById(Long id);

    Category save(Category category);

    Category updateById(Long id, Category category);

    void deleteById(Long id);

}
