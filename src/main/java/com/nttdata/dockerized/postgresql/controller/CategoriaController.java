package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.model.dto.CategoriaDto;
import com.nttdata.dockerized.postgresql.model.dto.CategoriaRequestDto;
import com.nttdata.dockerized.postgresql.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> findAll() {
        return ResponseEntity.ok(categoriaService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> findById(@PathVariable Long id) {
        CategoriaDto categoriaDto = categoriaService.findById(id);
        if (categoriaDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoriaDto);
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> save(@RequestBody CategoriaRequestDto categoriaRequestDto) {
        try {
            CategoriaDto saved = categoriaService.save(categoriaRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> update(@PathVariable Long id, @RequestBody CategoriaDto requestDto) {
        if (categoriaService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        requestDto.setId(id);
        CategoriaDto updated = categoriaService.update(requestDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (categoriaService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
