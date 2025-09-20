package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.model.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.nttdata.dockerized.postgresql.model.entity.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    public CategoryDto map(Category category);
    public List<CategoryDto> map(List<Category> categories);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    public Category toEntity(CategorySaveRequestDto categorySaveRequestDto);

    public CategorySaveResponseDto toCategorySaveResponseDto(Category category);

    default Category toEntityForUpdate(Long id, CategorySaveRequestDto dto, Category existingCategory) {
        if (dto == null) {
            throw new IllegalArgumentException("El DTO de actualización no puede ser null");
        }
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser null");
        }

        Category category = new Category();
        category.setId(id);
        category.setProducts(existingCategory.getProducts());

        if (dto.getName() != null && !dto.getName().trim().isEmpty()) {
            category.setName(dto.getName());
        } else {
            category.setName(existingCategory.getName());
        }

        return category;
    }
}
