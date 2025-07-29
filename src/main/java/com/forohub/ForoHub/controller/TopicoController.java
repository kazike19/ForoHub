package com.forohub.ForoHub.controller;

import com.forohub.ForoHub.domain.Topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    private PosteoDeTopicos posteo;

    @Autowired
    private ActualizarTopico actualizar;

    @Autowired
    private EliminarTopico eliminar;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<DatosDetalleTopico> publicar(@RequestBody @Valid DatosPosteoTopico datos){
        var detalleTopico = posteo.posteo(datos);

        return ResponseEntity.ok(detalleTopico);
    }

    @GetMapping("/listar")
    public  ResponseEntity<Page<DatosDetalleTopico>> listar(
            @PageableDefault(size= 10, sort = {"fechaCreacion"}, direction = Sort.Direction.ASC) Pageable paginacion){
        var page = topicoRepository.findAllByStatusTrue(paginacion).map(DatosDetalleTopico::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/listar/buscar")
    public  ResponseEntity<Page<DatosDetalleTopico>> listarPorNombre(
            @RequestParam(required = false) String nombreCurso,
            @RequestParam(required = false) Integer anio,
            @PageableDefault(size= 10) Pageable paginacion){

        Page<DatosDetalleTopico> page = topicoService.buscarTopicos(nombreCurso, anio, paginacion);
        return ResponseEntity.ok(page);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity detallarTopico(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }*/

    @GetMapping("/{id}/respuesta")
    public ResponseEntity<DatosDetalleTopico> detallarTopicoConRespuestas(@PathVariable Long id) {
        DatosDetalleTopico detalle = topicoService.obtenerDetalleTopico(id);
        return ResponseEntity.ok(detalle);
    }

    @PutMapping("/{id}")
    public ResponseEntity actualizarTopico(@PathVariable Long id,
                                           @RequestBody @Valid DatosActualizarTopico datos){
        var topicoActualizado = actualizar.actualizar(id, datos);

        return ResponseEntity.ok(topicoActualizado);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
       eliminar.eliminar(id);

       return ResponseEntity.noContent().build();
    }



}
