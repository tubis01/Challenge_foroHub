package com.alura.Challenge_foroHub.domain.Curso;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroCurso(
        @NotBlank
        String nombre,
        @JsonAlias("categoria")
        @NotNull
        Cursos cursos
) {
}
