package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.model.pedido.PedidoDto;
import com.nttdata.dockerized.postgresql.model.pedido.entity.Pedido;
import com.nttdata.dockerized.postgresql.model.pedido.entity.PedidoSveRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductoMapper.class})
public interface PedidoMapper {
    PedidoDto map(Pedido pedido);
    List<PedidoDto> map(List<Pedido> pedidos);

    Pedido toEntity(PedidoSveRequestDto dto);

    void updatePedidoFromDto(PedidoSveRequestDto dto, @MappingTarget Pedido pedido);

    default Pedido toEntity(PedidoSveRequestDto dto, Boolean estadoPorDefecto) {
        Pedido pedido = new Pedido();
        updatePedidoFromDto(dto, pedido);
        if(pedido.getEstado() == null) {
            pedido.setEstado(estadoPorDefecto != null ? estadoPorDefecto : true);
        }
        return pedido;
    }
}
