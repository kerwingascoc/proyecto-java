package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.dto.*;

import java.util.List;

public interface ProductService {
    List<ProductDto> listAll();
    ProductDto findById(Long id);
    ProductSaveResponseDto save(ProductSaveRequestDto request);
    ProductDto update(Long id, ProductSaveRequestDto request);
    void deleteById(Long id);
}
