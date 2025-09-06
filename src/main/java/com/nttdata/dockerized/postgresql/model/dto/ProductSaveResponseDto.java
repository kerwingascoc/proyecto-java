package com.nttdata.dockerized.postgresql.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSaveResponseDto {
    private Long id;
    private String name;
    private Double price;
    private Long categoryId;
    private String categoryName;
}
