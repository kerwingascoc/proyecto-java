package com.nttdata.dockerized.postgresql.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserSaveResponseDto {

    private Long id;

    private String name;

    private String email;

    private Date fechaRegistro;
}
