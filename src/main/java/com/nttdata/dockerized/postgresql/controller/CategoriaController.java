package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.model.dto.CategoriaDTO;
import com.nttdata.dockerized.postgresql.model.entity.Categoria;
import com.nttdata.dockerized.postgresql.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.nttdata.dockerized.postgresql.mapper.CategoriaMapper.INSTANCE;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Operation(description = "Para crear una categoria")
    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(CategoriaDTO categoriaDTO){
        Categoria categoriaGuardada = categoriaService.crearCategoria(INSTANCE.toCategoria(categoriaDTO));
        return new ResponseEntity<>(categoriaGuardada, HttpStatus.CREATED);
    }


}
