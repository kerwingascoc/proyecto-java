package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.entity.Categoria;
import com.nttdata.dockerized.postgresql.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria crearCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

}
