package com.nttdata.dockerized.postgresql.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DetallePedidoDto {
    private Long id;
    private ProductDto producto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
}
