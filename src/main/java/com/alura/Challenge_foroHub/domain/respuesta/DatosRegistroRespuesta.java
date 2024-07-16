package com.alura.Challenge_foroHub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuesta(
        @NotBlank
        String mensaje,

        @NotNull
        Long topico,

        @NotNull
        Long autor,

        @NotBlank
        String solucion
) {
}
