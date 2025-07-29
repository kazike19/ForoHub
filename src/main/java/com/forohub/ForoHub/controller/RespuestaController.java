package com.forohub.ForoHub.controller;

import com.forohub.ForoHub.domain.Respuesta.*;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @PostMapping
    public ResponseEntity<DatosDetalleRespuesta> crearRespuesta(@RequestBody @Valid DatosRegistroRespuesta datos,
                                                                UriComponentsBuilder uri){
        DatosDetalleRespuesta respuestaCreada = respuestaService.crearRespuesta(datos);

        URI url = uri.path("/respuestas/{id}").buildAndExpand(respuestaCreada.id()).toUri();
        return  ResponseEntity.created(url).body(respuestaCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosDetalleRespuesta> actualizarRespuesta(@PathVariable Long id,
                                                                     @RequestBody @Valid DatosActualizarRespuesta datos){
        DatosDetalleRespuesta respuestaActualizada = respuestaService.actualizarRespuesta(id, datos);

        return ResponseEntity.ok(respuestaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRespuesta(@PathVariable Long id){
        respuestaService.eliminarRespuesta(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/topico/{id}")
    public ResponseEntity<Page<DatosDetalleRespuesta>> listarRespuestasPorTopico(@PathVariable Long id,
                                                                                 @PageableDefault(size = 10, sort = {"fechaCreacion"}) Pageable paginacion) {
        Page<DatosDetalleRespuesta> respuestas = respuestaService.listarRespuestasPorTopico(id, paginacion);
        return ResponseEntity.ok(respuestas);
    }

}
