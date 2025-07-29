package com.forohub.ForoHub.domain.Curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosCurso(
        @NotBlank(message = "El nombre del curso no puede estar vacio")
        String nombre,
        @NotNull(message = "La categoria del curso no puede ser nula")
        Categoria categoria
        ) {

        /*public DatosCurso(DatosCurso curso) {
                this(curso.nombre(), curso.categoria());
        }*/
}
