package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.model.dto.CreateCategoriaDTO;
import com.nttdata.dockerized.postgresql.model.dto.ResponseCategoriaDTO;
import com.nttdata.dockerized.postgresql.model.dto.UpdateCategoriaDTO;
import com.nttdata.dockerized.postgresql.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<ResponseCategoriaDTO> crear(@RequestBody CreateCategoriaDTO dto) {
        ResponseCategoriaDTO creada = categoriaService.crearCategoria(dto);
        return new ResponseEntity<>(creada, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ResponseCategoriaDTO>> listar() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCategoriaDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.obtenerCategoriaId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCategoriaDTO> actualizar(@PathVariable Long id, @RequestBody UpdateCategoriaDTO dto) {
        return ResponseEntity.ok(categoriaService.actualizarCategoria(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}