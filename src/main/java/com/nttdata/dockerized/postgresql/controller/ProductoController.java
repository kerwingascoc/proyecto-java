package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.model.dto.CreateProductoDTO;
import com.nttdata.dockerized.postgresql.model.dto.ResponseProductDTO;
import com.nttdata.dockerized.postgresql.model.dto.UpdateProductoDTO;
import com.nttdata.dockerized.postgresql.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<ResponseProductDTO> crear(@RequestBody CreateProductoDTO dto) {
        ResponseProductDTO creado = productoService.crearProducto(dto);
        return new ResponseEntity<>(creado, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ResponseProductDTO>> listar() {
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProductDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerProductoId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseProductDTO> actualizar(@PathVariable Long id, @RequestBody UpdateProductoDTO dto) {
        return ResponseEntity.ok(productoService.actualizarProducto(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
