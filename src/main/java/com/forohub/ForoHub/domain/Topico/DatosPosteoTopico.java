package com.forohub.ForoHub.domain.Topico;

import com.forohub.ForoHub.domain.Curso.DatosCurso;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DatosPosteoTopico(
        @NotNull(message = "El id del usuario no puede ser nulo.")
        Long idUsuario,

        @NotBlank(message = "El titulo del topico no puede ser nulo o estar en vacio.")
        String titulo,

        @NotNull(message = "El mensaje puede estar en blanco pero no puede ser nulo.")
        String mensaje,

        @NotNull(message = "Los datos del curso no pueden ser nulos.")
        @Valid
        DatosCurso curso

        //@NotNull(message = "La fecha de creacion no puede ser nula.")
        //LocalDateTime fecha_creacion
        ) {
}
