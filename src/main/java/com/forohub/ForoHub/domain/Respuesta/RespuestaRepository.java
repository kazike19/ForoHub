package com.forohub.ForoHub.domain.Respuesta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RespuestaRepository extends JpaRepository<RespuestaModel, Long> {
    Page<RespuestaModel> findByTopicoId(Long topicoId, Pageable paginacion);
}
