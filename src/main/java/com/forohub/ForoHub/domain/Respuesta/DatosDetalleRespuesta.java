package com.forohub.ForoHub.domain.Respuesta;

import com.forohub.ForoHub.domain.Topico.DatosDetalleTopicoResumen;
import com.forohub.ForoHub.domain.Usuario.DatosDetalleUsuario;
import com.forohub.ForoHub.domain.Usuario.DatosDetalleUsuarioResumen;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id,
        String mensaje,
        DatosDetalleTopicoResumen topico, // Un resumen del tópico
        LocalDate fechaCreacion,
        DatosDetalleUsuarioResumen autor // Un resumen del autor
    ) {

    public DatosDetalleRespuesta(RespuestaModel respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                new DatosDetalleTopicoResumen(respuesta.getTopico()),
                respuesta.getFechaCreacion(),
                new DatosDetalleUsuarioResumen(respuesta.getAutor())
        );
    }

}
