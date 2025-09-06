package com.nttdata.dockerized.postgresql.service;


import com.nttdata.dockerized.postgresql.model.dto.ProductoDto;
import com.nttdata.dockerized.postgresql.model.dto.ProductoRequestDto;

import java.util.List;

public interface ProductoService {
    List<ProductoDto> findAll();
    ProductoDto findById(Long id);
    ProductoDto save(ProductoRequestDto productoRequestDto);
    ProductoDto update(ProductoDto productoDto);
    void deleteById(Long id);
}
