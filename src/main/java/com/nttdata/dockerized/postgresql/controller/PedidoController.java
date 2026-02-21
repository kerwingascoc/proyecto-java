package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.model.dto.CreatePedidoDTO;
import com.nttdata.dockerized.postgresql.model.dto.ResponsePedidoDTO;
import com.nttdata.dockerized.postgresql.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<ResponsePedidoDTO> crear(@RequestBody CreatePedidoDTO dto) {
        ResponsePedidoDTO creado = pedidoService.crearPedido(dto);
        return new ResponseEntity<>(creado, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ResponsePedidoDTO>> listar() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePedidoDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.obtenerPedidoId(id));
    }
}