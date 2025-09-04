package com.nttdata.dockerized.postgresql.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserSaveResponseDto {

    private String id;

    private String name;

    private String email;
}
