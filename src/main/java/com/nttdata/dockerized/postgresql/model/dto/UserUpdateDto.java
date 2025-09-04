package com.nttdata.dockerized.postgresql.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto {

    private String name;

    private String email;

    private String status;
}
