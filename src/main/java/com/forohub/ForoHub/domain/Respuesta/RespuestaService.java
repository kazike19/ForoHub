package com.forohub.ForoHub.domain.Respuesta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RespuestaService {
    DatosDetalleRespuesta crearRespuesta(DatosRegistroRespuesta datos);
    DatosDetalleRespuesta actualizarRespuesta(Long id, DatosActualizarRespuesta datos);
    void eliminarRespuesta(Long id);
    Page<DatosDetalleRespuesta> listarRespuestasPorTopico(Long id, Pageable paginacion);
}
