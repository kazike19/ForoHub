package com.forohub.ForoHub.domain.Topico;

import com.forohub.ForoHub.domain.Curso.CursoModel;
import com.forohub.ForoHub.domain.Curso.CursoService;
import com.forohub.ForoHub.domain.Usuario.UsuarioModel;
import com.forohub.ForoHub.domain.Usuario.UsuarioRepository;
import com.forohub.ForoHub.domain.ValidacionExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PosteoDeTopicos {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoService cursoService;

    @Transactional
    public DatosDetalleTopico posteo(DatosPosteoTopico datos) {


        UsuarioModel autor = usuarioRepository.findById(datos.idUsuario())
                .orElseThrow(()-> new ValidacionExcepcion("No existe el usuario con ese el Id: "+datos.idUsuario()));

        CursoModel cursoAsociado = cursoService.obtenerOCrearCurso(datos.curso());

        List<TopicoModel> topicoExistente = topicoRepository.findByTituloAndMensajeAndCurso(datos.titulo(), datos.mensaje(), cursoAsociado);

        if(!topicoExistente.isEmpty()){
            throw new ValidacionExcepcion("Ya existe un topico con el mismo titulo para este curso.");
        }

        TopicoModel topicoNuevo = new TopicoModel(autor, datos, cursoAsociado);

        TopicoModel topicoGuardado = topicoRepository.save(topicoNuevo);

        return new DatosDetalleTopico(topicoGuardado);
    }

}
