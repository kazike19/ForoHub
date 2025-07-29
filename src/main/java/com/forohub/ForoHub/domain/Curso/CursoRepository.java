package com.forohub.ForoHub.domain.Curso;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<CursoModel, Long> {
    Optional<CursoModel> findByNombreAndCategoria(String nombre, Categoria categoria);
}
