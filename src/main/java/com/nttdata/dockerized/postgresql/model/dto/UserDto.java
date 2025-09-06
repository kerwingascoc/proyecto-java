package com.nttdata.dockerized.postgresql.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class UserDto {

    private Long id;

    private String name;

    private LocalDate fechaRegistro;

    private String email;

    private String status;
}
