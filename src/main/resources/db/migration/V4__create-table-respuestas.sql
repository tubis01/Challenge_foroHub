CREATE TABLE respuestas (
    id SERIAL PRIMARY KEY,
    mensaje VARCHAR(100) NOT NULL,
    topico BIGINT NOT NULL,
    fechaCreacion TIMESTAMP NOT NULL,
    autor BIGINT NOT NULL,
    solucion VARCHAR(500) NOT NULL,
    FOREIGN KEY (topico) REFERENCES topicos(id),
    FOREIGN KEY (autor) REFERENCES usuarios(id)
);