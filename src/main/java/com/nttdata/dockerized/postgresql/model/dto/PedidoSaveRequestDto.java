package com.nttdata.dockerized.postgresql.model.dto;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoSaveRequestDto {
    private String active;
    private Long userId;
    private List<DetallePedidoSaveRequestDto> detalles;
}
