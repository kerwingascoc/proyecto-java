package com.nttdata.dockerized.postgresql.repository;

import com.nttdata.dockerized.postgresql.model.entity.DetallePedido;
import org.springframework.data.repository.CrudRepository;

public interface DetallePedidoRepository extends CrudRepository<DetallePedido, Long> {
}
