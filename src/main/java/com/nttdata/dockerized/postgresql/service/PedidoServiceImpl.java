package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.exceptions.ResourceNotFoundException;
import com.nttdata.dockerized.postgresql.model.entity.Pedido;
import com.nttdata.dockerized.postgresql.model.entity.User;
import com.nttdata.dockerized.postgresql.repository.PedidoRepository;
import com.nttdata.dockerized.postgresql.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService{

    private final PedidoRepository pedidoRepository;

    private final UserRepository userRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, UserRepository userRepository) {
        this.pedidoRepository = pedidoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Pedido guardarPedido(Long idUser, Pedido pedido) {
        // Validar que exista el usuario
        User user = userRepository.findById(idUser).orElseThrow(
                () -> new ResourceNotFoundException("El id del usuario indicado no existe")
        );
        // Asignar el usuario encontrado al pedido
        pedido.setUsuario(user);
        // Guardar el pedido
        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        // Retornar el pedido guardado
        return pedidoGuardado;
    }

}
