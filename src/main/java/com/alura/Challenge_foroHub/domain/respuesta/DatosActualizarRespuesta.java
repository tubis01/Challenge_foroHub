package com.alura.Challenge_foroHub.domain.respuesta;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarRespuesta(
        @NotNull
        Long id,
        String titulo,
        String solucion
) {
}
