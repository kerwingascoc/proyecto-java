package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.model.dto.ProductoDto;
import com.nttdata.dockerized.postgresql.model.dto.ProductoRequestDto;
import com.nttdata.dockerized.postgresql.model.entity.Producto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    ProductoDto toDto(Producto producto);
    Producto toEntity(ProductoDto productoDto);
    Producto toEntity(ProductoRequestDto productoRequestDto);

}
