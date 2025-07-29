package com.forohub.ForoHub.domain.Topico;

import com.forohub.ForoHub.domain.Curso.DatosDetalleCurso;
import com.forohub.ForoHub.domain.Respuesta.DatosDetalleRespuesta;
import com.forohub.ForoHub.domain.Usuario.DatosDetalleUsuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        Boolean status,
        DatosDetalleUsuario autor,
        DatosDetalleCurso curso,
        List<DatosDetalleRespuesta> respuestas
        ) {

    public DatosDetalleTopico(TopicoModel topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.isStatus(),
                new DatosDetalleUsuario(topico.getAutor()),
                new DatosDetalleCurso(topico.getCurso()),
                topico.getRespuestas() != null ?
                        topico.getRespuestas().stream()
                                .map(DatosDetalleRespuesta::new)
                                .collect(Collectors.toList())
                        : List.of()
        );
    }
}
