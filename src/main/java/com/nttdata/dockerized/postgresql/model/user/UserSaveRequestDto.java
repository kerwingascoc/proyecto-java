package com.nttdata.dockerized.postgresql.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserSaveRequestDto {

    @NotBlank
    @Size(min = 3)
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre no debe contener números")
    private String name;

    @NotBlank
    @Email(message = "El email debe ser válido")
    private String email;

}
