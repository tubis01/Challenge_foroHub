package com.alura.Challenge_foroHub.domain.topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String status,
        Long autor,
        String curso
) {

    public DatosDetalleTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechaCreacion(), topico.getStatus().name(),
                topico.getAutor().getId(), topico.getCurso().getNombre());
    }
}
