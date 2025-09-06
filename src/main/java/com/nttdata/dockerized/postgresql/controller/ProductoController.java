package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.model.dto.ProductoDTO;
import com.nttdata.dockerized.postgresql.model.entity.Producto;
import com.nttdata.dockerized.postgresql.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.nttdata.dockerized.postgresql.mapper.ProductoMapper.INSTANCE;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Operation(description = "Para crear un producto")
    @PostMapping("/categoria/{idCategoria}")
    public ResponseEntity<Producto> crearProducto(@PathVariable(value = "idCategoria") Long idCategoria, @RequestBody ProductoDTO productoDTO){

        Producto productoGuardado = productoService.guardarProducto(idCategoria, INSTANCE.toProducto(productoDTO));

        return new ResponseEntity<>(productoGuardado, HttpStatus.CREATED);

    }

}
