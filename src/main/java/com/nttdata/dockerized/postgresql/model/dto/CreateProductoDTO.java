package com.nttdata.dockerized.postgresql.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductoDTO {
    private String nombre;
    private BigDecimal precio;
    private Long categoriaId;
}
