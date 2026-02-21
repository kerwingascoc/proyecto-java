package com.nttdata.dockerized.postgresql.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSaveRequestDto {
    private String name;
    private Double price;
    private Long categoryId;
}
