package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.mapper.ProductoMapper;
import com.nttdata.dockerized.postgresql.model.product.ProductoDto;
import com.nttdata.dockerized.postgresql.model.product.entity.Producto;
import com.nttdata.dockerized.postgresql.service.product.ProductoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.nttdata.dockerized.postgresql.model.product.ProductoSaveRequestDto;
import com.nttdata.dockerized.postgresql.model.product.ProductoSaveResponseDto;
import com.nttdata.dockerized.postgresql.model.product.ProductoUpdateRequestDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/productos")
@AllArgsConstructor
@Validated
public class ProductoController {

    private final ProductoService productoService;
    private final ProductoMapper productoMapper;

    @PostMapping
    public ResponseEntity<ProductoSaveResponseDto> save(@Valid @RequestBody @NotNull ProductoSaveRequestDto productoSaveRequestDto) {
        Producto producto = productoMapper.toEntity(productoSaveRequestDto);
        Producto savedProducto = productoService.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productoMapper.toProductoSaveResponseDto(savedProducto));
    }

    @GetMapping
    public ResponseEntity<List<ProductoDto>> getAllProductos() {
        List<Producto> productos = productoService.listAll();
        return ResponseEntity.ok(productoMapper.map(productos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> getProductoById(@PathVariable Long id) {
        Producto producto = productoService.findById(id);
        return ResponseEntity.ok(productoMapper.map(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> updateProducto(@PathVariable Long id,
                                                      @Valid @RequestBody ProductoUpdateRequestDto productoUpdateRequestDto) {
        Producto productoToUpdate = productoMapper.toEntity(productoUpdateRequestDto);
        Producto updatedProducto = productoService.updateById(id, productoToUpdate);
        return ResponseEntity.ok(productoMapper.map(updatedProducto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}