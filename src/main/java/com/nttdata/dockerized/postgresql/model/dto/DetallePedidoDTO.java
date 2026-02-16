package com.nttdata.dockerized.postgresql.model.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedidoDTO {

    private Integer cantidad;

    private Double precioUnitario;

}
