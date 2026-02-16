package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.entity.Pedido;

public interface PedidoService {

    Pedido guardarPedido(Long idUser, Pedido pedido);

}
