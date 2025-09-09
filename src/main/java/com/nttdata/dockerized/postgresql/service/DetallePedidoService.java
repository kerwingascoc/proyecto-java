package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.entity.DetallePedido;

public interface DetallePedidoService {

    DetallePedido guardarDetallePedido(Long idPedido, Long idProducto, DetallePedido detallePedido);

}
