package com.forohub.ForoHub.domain.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DatosRegistroUsuario(
        @NotBlank(message = "El nombre no puede estar vacio.")
        String nombre,

        @NotBlank(message = "EL email no puede estar vacio.")
        @Email(message = "El formato no es valido")
        String email,

        @NotBlank(message = "La contraseña no puede estar vacia.")
        @Size(min = 6, message = "La contrasena debe tener al menos 6 caracteres")
        String contrasena
        ) {
}
