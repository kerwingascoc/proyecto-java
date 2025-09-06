package com.nttdata.dockerized.postgresql.model.product;

import lombok.Data;

@Data
public class ProductoSaveResponseDto {
    private String nombre;
    private Double precio;
}
