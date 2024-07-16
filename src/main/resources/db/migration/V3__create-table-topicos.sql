
CREATE TABLE topicos (
    id SERIAL  PRIMARY KEY ,
    titulo VARCHAR(200) NOT NULL,
    mensaje VARCHAR(500) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL,
    status VARCHAR(100) NOT NULL,
    autor BIGINT NOT NULL,
    curso BIGINT NOT NULL,
    respuestas INT DEFAULT 0,
    FOREIGN KEY (autor) REFERENCES usuarios(id),
    FOREIGN KEY (curso) REFERENCES cursos(id)
);
