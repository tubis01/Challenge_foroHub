package com.alura.Challenge_foroHub.domain.Curso;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarCurso(
        @NotNull
        Long id,
        String nombre,
        Cursos cursos
) {
}
