package com.alura.Challenge_foroHub.domain.respuesta;

import com.alura.Challenge_foroHub.domain.Curso.Cursos;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id,
        String topico,
        String mensaje,
        LocalDateTime fechaCreacion,
        String autor,
        String solucion
) {
    public DatosDetalleRespuesta(Respuesta respuesta) {
        this(respuesta.getId(),
                respuesta.getTopico().getTitulo(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getAutor().getNombre(),
                respuesta.getSolucion());
    }
}
