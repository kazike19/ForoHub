package com.forohub.ForoHub.domain.Curso;

public record DatosDetalleCurso(
        Long id,
        String nombre,
        String categoria
    ) {

    public DatosDetalleCurso(CursoModel curso){
        this(curso.getId(), curso.getNombre(), curso.getCategoria().toString());
    }
}
