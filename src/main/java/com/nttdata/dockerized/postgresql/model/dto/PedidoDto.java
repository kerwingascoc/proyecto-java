package com.nttdata.dockerized.postgresql.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PedidoDto {
    private Long id;
    private LocalDateTime fechaPedido;
    private String active;
    private UserDto user;
    private List<DetallePedidoDto> detallesPedido;
}
