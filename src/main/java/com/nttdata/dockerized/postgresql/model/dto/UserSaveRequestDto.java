package com.nttdata.dockerized.postgresql.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class UserSaveRequestDto {

    private String name;
    private String email;
    private String active;
}
