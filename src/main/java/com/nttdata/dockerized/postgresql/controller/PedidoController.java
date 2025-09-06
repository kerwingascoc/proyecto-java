package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.model.dto.*;
import com.nttdata.dockerized.postgresql.service.DetallePedidoService;
import com.nttdata.dockerized.postgresql.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.nttdata.dockerized.postgresql.mapper.PedidoMapper.INSTANCE;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final DetallePedidoService detallePedidoService;

    @GetMapping
    public List<PedidoDto> getAllPedidos() {
        return pedidoService.listAll().stream()
                .map(INSTANCE::map)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> getPedidoById(@PathVariable final Long id) {
        return pedidoService.findById(id)
                .map(INSTANCE::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clientId}/historial")
    public List<PedidoDto> getHistorialPedidosByClient(@PathVariable final Long clientId) {
        return pedidoService.findHistorialByClient(clientId).stream()
                .map(INSTANCE::map)
                .toList();
    }

    @PostMapping
    public PedidoSaveResponseDto crearPedido(@RequestBody final PedidoSaveRequestDto pedidoSaveRequestDto) {
        return INSTANCE.toPedidoSaveResponseDto(
                pedidoService.crearPedido(pedidoSaveRequestDto)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDto> update(@PathVariable final Long id, 
                                           @RequestBody final PedidoUpdateRequestDto pedidoUpdateRequestDto) {
        return pedidoService.update(id, INSTANCE.toEntity(pedidoUpdateRequestDto))
                .map(INSTANCE::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        return pedidoService.deleteById(id) 
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // Endpoints extras pedidos
    @GetMapping("/reportes/productos-mas-vendidos")
    public List<Map<String, Object>> getProductosMasVendidos() {
        return detallePedidoService.getProductosMasVendidos();
    }

    @GetMapping("/reportes/clientes-por-categoria")
    public List<Map<String, Object>> getClientesPorCategoria() {
        return detallePedidoService.getClientesPorCategoria();
    }
}
