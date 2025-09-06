package com.nttdata.dockerized.postgresql.model.product;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductoSaveRequestDto {

    @NotBlank
    @Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre no debe contener números ni caracteres especiales")
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que cero")
    private Double precio;
}
