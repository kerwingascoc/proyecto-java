package com.nttdata.dockerized.postgresql.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategorySaveResponseDto {
    private Long id;
    private String name;
}
