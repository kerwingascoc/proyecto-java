package com.nttdata.dockerized.postgresql.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserPatchDto {
    private String email;
    private Boolean active;
}