package com.nttdata.dockerized.postgresql.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserSaveRequestDto {

    private String name;

    private String email;
}
