package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.model.dto.ProductDTO.ProductDto;
import com.nttdata.dockerized.postgresql.model.dto.ProductDTO.ProductPatchDto;
import com.nttdata.dockerized.postgresql.model.dto.ProductDTO.ProductSaveRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.ProductDTO.ProductSaveResponseDto;
import com.nttdata.dockerized.postgresql.model.entity.Product;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);


    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    ProductDto toDto(Product product);

    List<ProductDto> toDtoList(List<Product> products);

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    ProductSaveResponseDto toSaveResponseDto(Product product);


    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductDto dto);

    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductSaveRequestDto dto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "category", ignore = true)
    void updateEntityFromPatchDto(ProductPatchDto dto, @MappingTarget Product entity);
}