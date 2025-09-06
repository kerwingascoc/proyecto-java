package com.nttdata.dockerized.postgresql.model.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {

    private String nombre;

    private Double precio;

}
