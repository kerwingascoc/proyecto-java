package com.nttdata.dockerized.postgresql.repository;

import com.nttdata.dockerized.postgresql.model.pedido.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
