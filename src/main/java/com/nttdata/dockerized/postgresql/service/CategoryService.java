package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.dto.*;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> listAll();
    CategoryDto findById(Long id);
    CategorySaveResponseDto save(CategorySaveRequestDto request);
    CategoryDto update(Long id, CategorySaveRequestDto request);
    void deleteById(Long id);
}
