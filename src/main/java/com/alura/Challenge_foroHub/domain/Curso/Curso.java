package com.alura.Challenge_foroHub.domain.Curso;

import com.alura.Challenge_foroHub.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Curso")
@Table(name = "cursos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private Cursos categoria;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Topico> topicos;

    private boolean activo;

    public Curso(DatosRegistroCurso registroCurso){
        this.nombre = registroCurso.nombre();
        this.categoria = registroCurso.cursos();
    }

    public void actualizarCurso(DatosActualizarCurso datosActualizarCurso){
        if(datosActualizarCurso.nombre() != null){
            this.nombre = datosActualizarCurso.nombre();
        }
        if(datosActualizarCurso.cursos() != null){
            this.categoria = datosActualizarCurso.cursos();
        }
    }

    public void desactivarCurso() {
        this.activo = false;
    }

    public void activarCurso() {
        this.activo = true;
    }
}
