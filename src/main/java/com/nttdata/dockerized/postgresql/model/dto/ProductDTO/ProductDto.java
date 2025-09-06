package com.nttdata.dockerized.postgresql.model.dto.ProductDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private Double price;
    private Boolean active;
    private Long categoryId;
    private String categoryName;
}