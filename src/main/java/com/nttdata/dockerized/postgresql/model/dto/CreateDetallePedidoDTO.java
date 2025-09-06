package com.nttdata.dockerized.postgresql.model.dto;

import lombok.Data;

@Data
public class CreateDetallePedidoDTO {
    private Long productoId;
    private Integer cantidad;
}
