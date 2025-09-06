package com.nttdata.dockerized.postgresql.model.user;

import com.nttdata.dockerized.postgresql.model.pedido.PedidoDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private LocalDateTime fechaRegistro;
    private Boolean active;
    private List<PedidoDto> pedidos;
}
