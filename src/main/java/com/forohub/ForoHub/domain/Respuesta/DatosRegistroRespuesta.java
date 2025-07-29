package com.forohub.ForoHub.domain.Respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuesta(
        @NotBlank(message = "El mensaje de la respuesta no puede estar en blanco")
        String mensaje,

        @NotNull(message = "El ID del topico es obligatorio")
        Long idTopico,

        @NotNull(message = "El ID del autor es obligatorio")
        Long idAutor
        ){
}
