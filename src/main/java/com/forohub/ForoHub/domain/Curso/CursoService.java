package com.forohub.ForoHub.domain.Curso;

import com.forohub.ForoHub.domain.ValidacionExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public CursoModel obtenerOCrearCurso(DatosCurso datosCurso){
        if(datosCurso.nombre() == null || datosCurso.nombre().trim().isEmpty()){
            throw new ValidacionExcepcion("El nombre del curso no puede ser nulo o estar vacio");
        }

        if(datosCurso.categoria() == null){
            throw new ValidacionExcepcion("La categoria del curso no puede ser nula o vacia");
        }


        Optional<CursoModel> cursoExistente = cursoRepository.findByNombreAndCategoria(datosCurso.nombre(), datosCurso.categoria());

        if(cursoExistente.isPresent()){
            System.out.println("Curso encontrado: nombre="+cursoExistente.get().getNombre()+", categoria: "+cursoExistente.get().getCategoria());
            return cursoExistente.get();
        }else {
            System.out.println("Creando curso");
            var cursoNuevo = new CursoModel(
                    null,
                    datosCurso.nombre(),
                    datosCurso.categoria(),
                    null
            );
            return cursoRepository.save(cursoNuevo);
        }


    }
}
