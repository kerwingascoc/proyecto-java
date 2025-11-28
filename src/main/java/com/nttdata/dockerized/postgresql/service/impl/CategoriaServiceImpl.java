package com.nttdata.dockerized.postgresql.service.impl;

import com.nttdata.dockerized.postgresql.mapper.CategoriaMapper;
import com.nttdata.dockerized.postgresql.model.dto.CategoriaDto;
import com.nttdata.dockerized.postgresql.model.dto.CategoriaRequestDto;
import com.nttdata.dockerized.postgresql.model.entity.Categoria;
import com.nttdata.dockerized.postgresql.repository.CategoriaRepository;
import com.nttdata.dockerized.postgresql.service.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public List<CategoriaDto> findAll() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toDto)
                .toList();
    }

    @Override
    public CategoriaDto findById(Long id) {
        return categoriaRepository.findById(id)
                .map(categoriaMapper::toDto)
                .orElse(null);
    }

    @Override
    public CategoriaDto save(CategoriaRequestDto categoriaRequestDtoDto) {
        Categoria categoria = categoriaMapper.toEntity(categoriaRequestDtoDto);
        Categoria saved = categoriaRepository.save(categoria);
        return categoriaMapper.toDto(saved);
    }

    @Override
    public CategoriaDto update(CategoriaDto categoriaDto) {
        Categoria categoria = categoriaMapper.toEntity(categoriaDto);
        Categoria updated = categoriaRepository.save(categoria);
        return categoriaMapper.toDto(updated);
    }

    @Override
    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }
}