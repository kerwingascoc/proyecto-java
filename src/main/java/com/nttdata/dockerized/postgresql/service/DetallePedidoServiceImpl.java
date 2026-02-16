package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.exceptions.ResourceNotFoundException;
import com.nttdata.dockerized.postgresql.model.entity.DetallePedido;
import com.nttdata.dockerized.postgresql.model.entity.Pedido;
import com.nttdata.dockerized.postgresql.model.entity.Producto;
import com.nttdata.dockerized.postgresql.repository.DetallePedidoRepository;
import com.nttdata.dockerized.postgresql.repository.PedidoRepository;
import com.nttdata.dockerized.postgresql.repository.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService{

    private final DetallePedidoRepository detallePedidoRepository;

    private final PedidoRepository pedidoRepository;

    private final ProductoRepository productoRepository;

    public DetallePedidoServiceImpl(DetallePedidoRepository detallePedidoRepository,
                                    PedidoRepository pedidoRepository,
                                    ProductoRepository productoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public DetallePedido guardarDetallePedido(Long idPedido, Long idProducto, DetallePedido detallePedido) {
        // Verificar que exista el pedido
        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow(
                () -> new ResourceNotFoundException("El id del pedido indicado no existe")
        );
        // Verificar que exista el producto
        Producto producto = productoRepository.findById(idProducto).orElseThrow(
                () -> new ResourceNotFoundException("El id del producto indicado no existe")
        );
        // Asignar el pedido y producto al detalle pedido
        detallePedido.setPedido(pedido);
        detallePedido.setProducto(producto);
        // Guardar el detalle pedido
        DetallePedido detallePedidoGuardado = detallePedidoRepository.save(detallePedido);
        // Retornar el detalle pedido guardado
        return detallePedidoGuardado;
    }

}
