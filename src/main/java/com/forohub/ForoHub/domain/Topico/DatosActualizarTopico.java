package com.forohub.ForoHub.domain.Topico;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizarTopico(
        String titulo,
        String mensaje,
        Long idCurso
        ) {
}
