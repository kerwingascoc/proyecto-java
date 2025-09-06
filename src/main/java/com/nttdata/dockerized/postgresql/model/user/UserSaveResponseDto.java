package com.nttdata.dockerized.postgresql.model.user;

import lombok.Data;

@Data
public class UserSaveResponseDto {
    private String name;
    private String email;
    private Boolean active;
}