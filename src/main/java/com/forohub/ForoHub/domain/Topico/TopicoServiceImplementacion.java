package com.forohub.ForoHub.domain.Topico;

import com.forohub.ForoHub.domain.Respuesta.RespuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TopicoServiceImplementacion implements TopicoService{
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Override
    public Page<DatosDetalleTopico> buscarTopicos(String nombreCurso, Integer anio, Pageable paginacion){
        Page<TopicoModel> topicoPage;

        if(nombreCurso != null && anio != null){
            String nombreCursoP = "%" + nombreCurso + "%";
            topicoPage = topicoRepository.findTopicosByAnioAndCursoNombre(nombreCursoP, anio, paginacion);

        } else if (nombreCurso != null) {
            topicoPage = topicoRepository.findAllByStatusTrueAndCurso_NombreContainingIgnoreCase(nombreCurso, paginacion);

        } else if(anio != null){
            topicoPage = topicoRepository.findTopicosByAnio(anio, paginacion);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debes de dar al menos un criterio para la busqueda, nombre o año");
        }

        return topicoPage.map(DatosDetalleTopico::new);
    }

    @Override
    public DatosDetalleTopico obtenerDetalleTopico(Long id) {
        TopicoModel topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El topico con id " + id + " no existe. Intenta nuevamente"));

        topico.getRespuestas().size();

        return new DatosDetalleTopico(topico);
    }
}
