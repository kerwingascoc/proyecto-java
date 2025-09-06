package com.nttdata.dockerized.postgresql.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import com.nttdata.dockerized.postgresql.model.dto.*;
import com.nttdata.dockerized.postgresql.model.entity.Category;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto map(Category category);

    List<CategoryDto> map(List<Category> categories);

    Category toEntity(CategorySaveRequestDto dto);

    Category toEntity(CategoryUpdateRequestDto dto);

    CategorySaveResponseDto toCategorySaveResponseDto(Category category);

    @AfterMapping
    default void setRemainingValues(Category category, @MappingTarget CategoryDto dto) {
        dto.setId(category.getId());
    }
}
