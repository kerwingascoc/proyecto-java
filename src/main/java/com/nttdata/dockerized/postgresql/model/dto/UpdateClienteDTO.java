package com.nttdata.dockerized.postgresql.model.dto;

import lombok.Data;

@Data
public class UpdateClienteDTO {
    private String nombre;
    private String email;
    private boolean activo;
}