package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.model.product.ProductoDto;
import com.nttdata.dockerized.postgresql.model.product.ProductoSaveRequestDto;
import com.nttdata.dockerized.postgresql.model.product.ProductoSaveResponseDto;
import com.nttdata.dockerized.postgresql.model.product.ProductoUpdateRequestDto;
import com.nttdata.dockerized.postgresql.model.product.entity.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoDto map(Producto producto);
    List<ProductoDto> map(List<Producto> productos);

    Producto toEntity(ProductoSaveRequestDto productoSaveRequestDto);

    void updateUserFromDto(ProductoUpdateRequestDto dto, @MappingTarget Producto producto);

    ProductoSaveResponseDto toProductoSaveResponseDto(Producto producto);

    default Producto toEntity(ProductoUpdateRequestDto dto) {
        Producto producto = new Producto();
        updateUserFromDto(dto, producto);
        return producto;
    }
}
