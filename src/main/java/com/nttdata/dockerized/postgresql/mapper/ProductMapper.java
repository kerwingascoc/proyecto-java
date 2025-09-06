package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.model.dto.ProductDto;
import com.nttdata.dockerized.postgresql.model.dto.ProductSaveRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.ProductSaveResponseDto;
import com.nttdata.dockerized.postgresql.model.dto.ProductUpdateRequestDto;
import com.nttdata.dockerized.postgresql.model.entity.Product;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto map(Product product);

    List<ProductDto> map(List<Product> products);

    Product toEntity(ProductSaveRequestDto productSaveRequestDto);

    Product toEntity(ProductUpdateRequestDto productUpdateRequestDto);

    ProductSaveResponseDto toProductSaveResponseDto(Product product);

    @AfterMapping
    default void setCategoryInfo(Product product, @MappingTarget ProductDto productDto) {
        if (product.getCategory() != null) {
            productDto.setCategoryId(product.getCategory().getId());
            productDto.setCategoryName(product.getCategory().getName());
        }
    }
}
