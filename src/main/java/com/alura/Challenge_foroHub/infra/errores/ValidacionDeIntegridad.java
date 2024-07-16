package com.alura.Challenge_foroHub.infra.errores;

import javax.management.relation.RelationNotFoundException;

public class ValidacionDeIntegridad extends RuntimeException {
    public ValidacionDeIntegridad(String mensaje) {
        super(mensaje);
    }
}
