package com.nttdata.dockerized.postgresql.service.pedido;

import com.nttdata.dockerized.postgresql.model.pedido.entity.Pedido;
import com.nttdata.dockerized.postgresql.model.user.entity.User;

import java.util.List;

public interface PedidoService {
    //Crear
    Pedido save(Pedido pedido);

    //Obtener
    List<Pedido> listAll();

    //ObtenerPorId
    Pedido findById(Long id);

    //Actualizar
    Pedido updateById(Long id, Pedido user);

    //Eliminar
    void deleteById(Long id);
}
