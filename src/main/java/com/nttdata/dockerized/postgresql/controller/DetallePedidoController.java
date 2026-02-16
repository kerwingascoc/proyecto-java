package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.model.dto.DetallePedidoDTO;
import com.nttdata.dockerized.postgresql.model.entity.DetallePedido;
import com.nttdata.dockerized.postgresql.service.DetallePedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.nttdata.dockerized.postgresql.mapper.DetallePedidoMapper.INSTANCE;

@RestController
@RequestMapping("/api/detallepedidos")
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

    public DetallePedidoController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @PostMapping("/pedido/{idPedido}/producto/{idProducto}")
    public ResponseEntity<DetallePedido> guardarDetallePedido(@PathVariable("idPedido") Long idPedido,
                                                              @PathVariable("idProducto") Long idProducto,
                                                              @RequestBody DetallePedidoDTO detallePedidoDTO){

        DetallePedido detallePedido = detallePedidoService.guardarDetallePedido(idPedido, idProducto, INSTANCE.toDetallePedido(detallePedidoDTO));

        return new ResponseEntity<>(detallePedido, HttpStatus.CREATED);

    }

}
