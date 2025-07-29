package com.forohub.ForoHub.domain.Topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicoService {

    Page<DatosDetalleTopico> buscarTopicos(String nombreCurso, Integer anio, Pageable paginacion);

    DatosDetalleTopico obtenerDetalleTopico(Long id);
}
