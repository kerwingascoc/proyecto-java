package com.nttdata.dockerized.postgresql.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDto {
    private Long id;

    private String nombre;

    private Double precio;

    private Integer stock;

    private Long categoriaId;
}
