package com.forohub.ForoHub.domain.Topico;

import com.forohub.ForoHub.domain.ValidacionExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EliminarTopico {
    @Autowired
    TopicoRepository topicoRepository;

    public void eliminar(Long id){
        if(!topicoRepository.existsById(id)){
            throw new ValidacionExcepcion("El topico con el id: "+id+" no existe");
        }

        var topico = topicoRepository.getReferenceById(id);
        topico.eliminar();

    }
}
