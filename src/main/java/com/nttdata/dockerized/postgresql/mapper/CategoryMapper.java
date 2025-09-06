package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.model.dto.CategoryDto;
import com.nttdata.dockerized.postgresql.model.dto.CategoryRequestDto;
import com.nttdata.dockerized.postgresql.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {

    CategoryMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryMapper.class);

    public CategoryDto map(Category category);

    public List<CategoryDto> map(List<Category> categories);

    public Category toEntity(CategoryRequestDto userSaveRequestDto);
}
