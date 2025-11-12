package com.nttdata.dockerized.postgresql.model.dto.CategoryDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategorySaveResponseDto {
    private Long id;
    private String name;
}
