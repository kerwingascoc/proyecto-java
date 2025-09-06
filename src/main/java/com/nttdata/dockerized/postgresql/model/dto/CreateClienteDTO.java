package com.nttdata.dockerized.postgresql.model.dto;

import lombok.Data;

@Data
public class CreateClienteDTO {
    private String nombre;
    private String email;
}
