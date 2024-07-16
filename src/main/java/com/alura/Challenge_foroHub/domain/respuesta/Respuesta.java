package com.alura.Challenge_foroHub.domain.respuesta;

import com.alura.Challenge_foroHub.domain.topico.Topico;
import com.alura.Challenge_foroHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "Respuesta")
@Table(name = "respuestas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico", referencedColumnName = "id")
    private Topico topico;

    @Column(name = "fechacreacion")
    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor", referencedColumnName = "id")
    private Usuario autor;

    private String solucion;


    public Respuesta(DatosRegistroRespuesta datosRespuesta, Topico topico, Usuario usuario){
        this.mensaje = datosRespuesta.mensaje();
        this.topico = topico;
        this.fechaCreacion = LocalDateTime.now();
        this.autor = usuario;
        this.solucion = datosRespuesta.solucion();
    }

    public Respuesta actualizarRespuesta(DatosActualizarRespuesta datos){
        if(datos.titulo() != null){
            this.mensaje = datos.titulo();
        }
        if(datos.solucion() != null){
            this.solucion = datos.solucion();
        }
        return this;
    }

}
