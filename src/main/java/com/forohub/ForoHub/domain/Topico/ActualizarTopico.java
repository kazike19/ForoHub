package com.forohub.ForoHub.domain.Topico;

import com.forohub.ForoHub.domain.Curso.CursoModel;
import com.forohub.ForoHub.domain.Curso.CursoRepository;
import com.forohub.ForoHub.domain.Usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class ActualizarTopico {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;


    @Transactional
    public DatosDetalleTopico actualizar(Long id, DatosActualizarTopico datos) {
        TopicoModel topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico con ID " + id + " no encontrado."));

        // Captura los valores que el topico tedria después de la actualizacion
        String nuevoTitulo = (datos.titulo() != null && !datos.titulo().isBlank()) ? datos.titulo() : topico.getTitulo();
        String nuevoMensaje = (datos.mensaje() != null && !datos.mensaje().isBlank()) ? datos.mensaje() : topico.getMensaje();

        // Si DatosActualizarTopico puede actualizar el curso
        CursoModel nuevoCurso = topico.getCurso(); // Por defecto, usa el curso actual del tópico
        if (datos.idCurso() != null) {
            nuevoCurso = cursoRepository.findById(datos.idCurso())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso con ID " + datos.idCurso() + " no encontrado."));
        }

        List<TopicoModel> topicosExistentes = topicoRepository.findByTituloAndMensajeAndCurso(nuevoTitulo, nuevoMensaje, nuevoCurso);

        // Itera sobre la lista para encontrar un duplicado que NO sea el tópico actual
        for (TopicoModel existente : topicosExistentes) {
            if (!existente.getId().equals(id)) { // Si encontramos un tópico con los mismos datos, pero con un ID diferente
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un tópico con el mismo título, mensaje y curso.");
            }
        }

        // Si llegamos aquí, no se encontro ningun duplicado, procede a actualizar el topico
        topico.setTitulo(nuevoTitulo);
        topico.setMensaje(nuevoMensaje);
        topico.setCurso(nuevoCurso); // Aplica el nuevo curso si se proporciono


        return new DatosDetalleTopico(topico);
    }
}
