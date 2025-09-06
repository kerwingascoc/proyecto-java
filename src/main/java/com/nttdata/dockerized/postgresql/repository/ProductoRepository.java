package com.nttdata.dockerized.postgresql.repository;

import com.nttdata.dockerized.postgresql.model.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto, Long> {
}
