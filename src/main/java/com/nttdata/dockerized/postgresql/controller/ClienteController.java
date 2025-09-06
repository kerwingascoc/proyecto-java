package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.model.dto.CreateClienteDTO;
import com.nttdata.dockerized.postgresql.model.dto.ResponseClienteDTO;
import com.nttdata.dockerized.postgresql.model.dto.UpdateClienteDTO;
import com.nttdata.dockerized.postgresql.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ResponseClienteDTO> crear(@RequestBody CreateClienteDTO dto) {
        ResponseClienteDTO creado = clienteService.crearCliente(dto);
        return new ResponseEntity<>(creado, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ResponseClienteDTO>> listar() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseClienteDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.obtenerClienteId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseClienteDTO> actualizar(@PathVariable Long id, @RequestBody UpdateClienteDTO dto) {
        return ResponseEntity.ok(clienteService.actualizarCliente(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
