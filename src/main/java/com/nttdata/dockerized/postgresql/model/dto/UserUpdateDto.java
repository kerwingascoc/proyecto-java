package com.nttdata.dockerized.postgresql.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserUpdateDto {

    private String name;

    private LocalDate fechaRegistro;

    private String email;

    private String status;
}
