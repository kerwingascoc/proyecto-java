package com.nttdata.dockerized.postgresql.service.product;

import com.nttdata.dockerized.postgresql.model.product.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    // Crear
    Producto save(Producto producto);

    // Obtener todos
    List<Producto> listAll();

    // Obtener por ID
    Producto findById(Long id);

    // Actualizar por ID
    Producto updateById(Long id, Producto producto);

    // Eliminar por ID
    void deleteById(Long id);
}
