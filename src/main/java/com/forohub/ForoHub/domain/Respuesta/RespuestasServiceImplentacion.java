package com.forohub.ForoHub.domain.Respuesta;

import com.forohub.ForoHub.domain.Topico.TopicoModel;
import com.forohub.ForoHub.domain.Topico.TopicoRepository;
import com.forohub.ForoHub.domain.Usuario.UsuarioModel;
import com.forohub.ForoHub.domain.Usuario.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class RespuestasServiceImplentacion implements RespuestaService{
    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public DatosDetalleRespuesta crearRespuesta(DatosRegistroRespuesta datos){
        TopicoModel topico = topicoRepository.findById(datos.idTopico())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El topico con id "+datos.idTopico()+" no existe. Intenta nuevamente"));

        UsuarioModel autor = usuarioRepository.findById(datos.idAutor())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario con id "+datos.idAutor()+" no existe. Intenta nuevamente"));

        RespuestaModel respuesta = new RespuestaModel(
                null,
                datos.mensaje(),
                topico,
                LocalDate.now(),
                autor
        );
        respuesta = respuestaRepository.save(respuesta);


        return new DatosDetalleRespuesta(respuesta);
    }


    @Override
    @Transactional
    public DatosDetalleRespuesta actualizarRespuesta(Long id, DatosActualizarRespuesta datos){
        RespuestaModel respuesta = respuestaRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "La respuesta con el id " + id +" no existe. Intenta nuevamente"));

        if(datos.mensaje() != null && !datos.mensaje().isBlank()){
            respuesta.setMensaje(datos.mensaje());
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El mensaje que se va actualizar no puede estar vacio.");
        }

        return new DatosDetalleRespuesta(respuesta);
    }

    @Override
    @Transactional
    public void eliminarRespuesta(Long id) {
        if(!respuestaRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La respuesta con id " + id + " no existe. Intenta nuevamente");
        }

        respuestaRepository.deleteById(id);

    }

    @Override
    public Page<DatosDetalleRespuesta> listarRespuestasPorTopico(Long id, Pageable paginacion) {
        if(!topicoRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El topico con id " + id + " no existe. Intenta nuevamente");
        }
        Page<RespuestaModel> respuestas = respuestaRepository.findByTopicoId(id, paginacion);

        return respuestas.map(DatosDetalleRespuesta::new);
    }
}
