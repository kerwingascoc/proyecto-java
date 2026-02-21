package com.nttdata.dockerized.postgresql.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreatePedidoDTO {
    private Long clienteId;
    private LocalDate fechaPedido;
    private List<CreateDetallePedidoDTO> detalles;
}