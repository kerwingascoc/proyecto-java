package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.mapper.PedidoMapper;
import com.nttdata.dockerized.postgresql.model.pedido.PedidoDto;
import com.nttdata.dockerized.postgresql.model.pedido.entity.Pedido;
import com.nttdata.dockerized.postgresql.model.product.entity.Producto;
import com.nttdata.dockerized.postgresql.service.pedido.PedidoService;
import com.nttdata.dockerized.postgresql.service.product.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/pedidos")
@AllArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;
    private final ProductoService productoService;
    private final PedidoMapper pedidoMapper;

    @PostMapping("/{pedidoId}/productos/{productoId}")
    public ResponseEntity<PedidoDto> addProductoToPedido(@PathVariable Long pedidoId,
                                                         @PathVariable Long productoId) {
        Pedido pedido = pedidoService.findById(pedidoId);
        Producto producto = productoService.findById(productoId);

        if (pedido.getProductos() == null) pedido.setProductos(new ArrayList<>());
        pedido.getProductos().add(producto);

        Pedido savedPedido = pedidoService.save(pedido);
        return ResponseEntity.ok(pedidoMapper.map(savedPedido));
    }
}
