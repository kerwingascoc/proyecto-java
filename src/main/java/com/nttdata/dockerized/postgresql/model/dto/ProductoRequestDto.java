package com.nttdata.dockerized.postgresql.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoRequestDto {
    private String nombre;

    private Double precio;

    private Integer stock;

    private Long categoriaId;

}
