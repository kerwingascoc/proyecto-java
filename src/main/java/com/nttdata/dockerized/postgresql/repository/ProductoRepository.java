package com.nttdata.dockerized.postgresql.repository;

import com.nttdata.dockerized.postgresql.model.product.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
