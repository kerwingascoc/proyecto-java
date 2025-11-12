package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.model.dto.CategoryDTO.CategoryDto;
import com.nttdata.dockerized.postgresql.model.dto.CategoryDTO.CategoryPatchDto;
import com.nttdata.dockerized.postgresql.model.dto.CategoryDTO.CategorySaveRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.CategoryDTO.CategorySaveResponseDto;
import com.nttdata.dockerized.postgresql.model.entity.Category;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto map(Category category);
    List<CategoryDto> map(List<Category> categories);
    CategorySaveResponseDto toCategorySaveResponseDto(Category category);


    Category map(CategoryDto dto);
    Category toEntity(CategorySaveRequestDto dto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategoryFromPatchDto(CategoryPatchDto dto, @MappingTarget Category entity);
}