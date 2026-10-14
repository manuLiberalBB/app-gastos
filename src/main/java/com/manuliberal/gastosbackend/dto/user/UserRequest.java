package com.manuliberal.gastosbackend.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "El nombre no puede estar vacío")
        String name,

        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El formato del email es inválido")
        String email,

        @NotBlank(message = "La contraseña no puede estar vacía")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String password
) {
}