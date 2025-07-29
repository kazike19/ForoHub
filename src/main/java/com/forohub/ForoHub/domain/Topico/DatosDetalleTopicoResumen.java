package com.forohub.ForoHub.domain.Topico;

public record DatosDetalleTopicoResumen(
        Long id,
        String titulo
        ) {
    public DatosDetalleTopicoResumen(TopicoModel topico) {
        this(topico.getId(), topico.getTitulo());
    }
}
