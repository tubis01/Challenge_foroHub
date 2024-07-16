package com.alura.Challenge_foroHub.controller;

import com.alura.Challenge_foroHub.domain.Curso.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursosController {

    private final CursoService cursoService;

    public CursosController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    //listar todos los cursos
    @Operation(summary = "Obtiene el listado de todos los cursos")
    @GetMapping("/listar")
    public ResponseEntity<Page<DatosDetalleCurs>> listarCursos(@PageableDefault(size = 5) Pageable pageable){
        return ResponseEntity.ok(cursoService.listarCursos(pageable));
    }

    //obtener un curso por id
    @Operation(summary = "Obtiene un curso por su id")
    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleCurs> obtenerCurso(@PathVariable Long id){
        Curso curso = cursoService.obtenerCurso(id);
        var respuesta = new DatosDetalleCurs(curso);
        return ResponseEntity.ok(respuesta);
    }

    //registrar curso
    @Operation(summary = "Registra un curso")
    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<DatosDetalleCurs> registrarCurso(@RequestBody @Valid DatosRegistroCurso datos,
                                                         UriComponentsBuilder uriBuilder){
        Curso curso = cursoService.registrarCurso(datos);
        var respuesta = new DatosDetalleCurs(curso);
        URI uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(respuesta);
    }

    //modificar curso
    @Operation(summary = "Modifica un curso")
    @PutMapping("/modificar")
    @Transactional
    public ResponseEntity<DatosDetalleCurs> modificarCurso(@RequestBody @Valid DatosActualizarCurso datos){
        var respuesta = cursoService.actualizarCurso(datos);
        return ResponseEntity.ok(respuesta);
    }

    @Operation(summary = "Deshabilita un curso")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> eliminarCurso(@PathVariable Long id){
        cursoService.deshabilitarCurso(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Habilita un curso")
    @PatchMapping("/{id}/activar")
    @Transactional
    public ResponseEntity<Object> activarCurso(@PathVariable Long id){
        cursoService.habilitarCurso(id);
        return ResponseEntity.noContent().build();
    }
}
