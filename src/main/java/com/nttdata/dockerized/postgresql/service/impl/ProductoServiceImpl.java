package com.nttdata.dockerized.postgresql.service.impl;

import com.nttdata.dockerized.postgresql.mapper.ProductoMapper;
import com.nttdata.dockerized.postgresql.model.dto.ProductoDto;
import com.nttdata.dockerized.postgresql.model.dto.ProductoRequestDto;
import com.nttdata.dockerized.postgresql.model.entity.Categoria;
import com.nttdata.dockerized.postgresql.model.entity.Producto;
import com.nttdata.dockerized.postgresql.repository.CategoriaRepository;
import com.nttdata.dockerized.postgresql.repository.ProductoRepository;
import com.nttdata.dockerized.postgresql.service.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoMapper productoMapper;

    public ProductoServiceImpl(ProductoRepository productoRepository, CategoriaRepository categoriaRepository, ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.productoMapper = productoMapper;
    }

    @Override
    public List<ProductoDto> findAll() {
        return productoRepository.findAll()
                .stream()
                .map(productoMapper::toDto)
                .toList();
    }

    @Override
    public ProductoDto findById(Long id) {
        return productoRepository.findById(id)
                .map(productoMapper::toDto)
                .orElse(null);
    }

    @Override
    public ProductoDto save(ProductoRequestDto productoRequestDto) {
        Categoria categoria = categoriaRepository.findById(productoRequestDto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("La categoria con ID " + productoRequestDto.getCategoriaId() + " no existe"));
        Producto producto = productoMapper.toEntity(productoRequestDto);
        producto.setCategoria(categoria);
        Producto saved = productoRepository.save(producto);
        return productoMapper.toDto(saved);
    }

    @Override
    public ProductoDto update(ProductoDto productoDto) {
        Categoria categoriaDb = categoriaRepository.findById(productoDto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("La categoria con ID " + productoDto.getCategoriaId() + " no existe"));

        Producto producto = productoMapper.toEntity(productoDto);
        producto.setCategoria(categoriaDb);
        Producto updated = productoRepository.save(producto);
        return productoMapper.toDto(updated);
    }

    @Override
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }
}
