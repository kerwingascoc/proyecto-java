package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.dto.CategoriaDto;
import com.nttdata.dockerized.postgresql.model.dto.CategoriaRequestDto;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<CategoriaDto> findAll();
    CategoriaDto findById(Long id);
    CategoriaDto save(CategoriaRequestDto categoriaDto);
    CategoriaDto update(CategoriaDto categoriaDto);
    void deleteById(Long id);
}
