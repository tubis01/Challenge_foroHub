package com.alura.Challenge_foroHub.domain.Curso;

public record DatosDetalleCurs(
        Long id,
        String nombre,
        Cursos curso

) {
    public DatosDetalleCurs(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
