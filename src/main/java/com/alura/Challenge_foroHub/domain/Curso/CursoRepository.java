package com.alura.Challenge_foroHub.domain.Curso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Page<Curso> findCursoByActivoTrue(Pageable pageable);
    @Query("""
            SELECT
            CASE
                WHEN COUNT(c) > 0 THEN TRUE
                ELSE FALSE
            END
            FROM Curso c
            WHERE LOWER(c.nombre) = LOWER(:nombre)
            """)
    boolean existsByNombreIgnoreCase(String nombre);
}
