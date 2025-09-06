package com.nttdata.dockerized.postgresql.model.pedido;

import com.nttdata.dockerized.postgresql.model.product.ProductoDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoDto {
    private Long id;
    private LocalDateTime fechaPedido;
    private Boolean estado;
    private List<ProductoDto> productos;

}
