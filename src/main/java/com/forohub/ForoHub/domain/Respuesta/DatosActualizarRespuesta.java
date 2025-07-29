package com.forohub.ForoHub.domain.Respuesta;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizarRespuesta(
        @NotBlank(message = "El mensaje de la respuesta no puede estar en blanco")
        String mensaje
        ) {
}
