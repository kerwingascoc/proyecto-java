package com.nttdata.dockerized.postgresql.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserDto {

    private String id;

    private String name;

    private String email;

    private String status;
}
