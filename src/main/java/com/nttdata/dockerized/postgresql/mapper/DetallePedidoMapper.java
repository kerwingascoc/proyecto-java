package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.model.dto.DetallePedidoDTO;
import com.nttdata.dockerized.postgresql.model.entity.DetallePedido;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DetallePedidoMapper {

    DetallePedidoMapper INSTANCE = Mappers.getMapper(DetallePedidoMapper.class);

    DetallePedido toDetallePedido(DetallePedidoDTO detallePedidoDTO);

}
