package com.nttdata.dockerized.postgresql.service;



import com.nttdata.dockerized.postgresql.model.dto.CategoryRequestDto;
import com.nttdata.dockerized.postgresql.model.entity.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> listAll();

    public Category findById(Long id);

    public Category save(Category user);

    public Category update(CategoryRequestDto user, Long id);
    public void delete(Long id);
}
