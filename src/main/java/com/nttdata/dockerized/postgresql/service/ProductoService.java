package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.entity.Producto;

public interface ProductoService {

    Producto guardarProducto(Long idCategoria, Producto producto);

}
