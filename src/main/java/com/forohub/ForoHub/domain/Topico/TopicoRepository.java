package com.forohub.ForoHub.domain.Topico;


import com.forohub.ForoHub.domain.Curso.CursoModel;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository <TopicoModel, Long>{

    //Optional<TopicoModel> findByTituloAndMensajeAndCurso(@NotBlank String titulo, String mensaje, CursoModel curso);

    List<TopicoModel> findByTituloAndMensajeAndCurso(@NotBlank String titulo, String mensaje, CursoModel curso);

    Page<TopicoModel> findAllByStatusTrue(Pageable paginacion);

    @Query(value = """
    SELECT t.* FROM topicos t
    JOIN cursos c ON t.curso_id = c.id
    WHERE t.status = true
        AND YEAR(t.fecha_creacion) = :anio
        AND c.nombre LIKE :nombreCursoPattern
    ORDER BY c.nombre ASC
    """,
            countQuery = """
    SELECT COUNT(*) FROM topicos t
    JOIN cursos c ON t.curso_id = c.id
    WHERE t.status = true
      AND YEAR(t.fecha_creacion) = :anio
      AND c.nombre LIKE :nombreCursoPattern
    """,
            nativeQuery = true)
    Page<TopicoModel> findTopicosByAnioAndCursoNombre(@Param("nombreCursoPattern") String nombreCursoPattern, @Param("anio") Integer anio, Pageable paginacion);

    Page<TopicoModel> findAllByStatusTrueAndCurso_NombreContainingIgnoreCase(String nombreCurso, Pageable paginacion);

    @Query(value = """
        SELECT * FROM topicos t
        WHERE t.status = true
        AND YEAR(t.fecha_creacion) = :anio
        """, nativeQuery = true)
    Page<TopicoModel> findTopicosByAnio(@Param("anio") Integer anio, Pageable paginacion);
}
