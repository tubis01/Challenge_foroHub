package com.alura.Challenge_foroHub.controller;

import com.alura.Challenge_foroHub.domain.respuesta.*;
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
@RequestMapping("/respuesta")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    private final RespuestaService respuestaService;

    public RespuestaController(RespuestaService respuestaService) {
        this.respuestaService = respuestaService;
    }

    //listar respuestas
    @Operation(summary = "Listar las respuestas")
    @GetMapping("/listar")
    public ResponseEntity<Page<DatosDetalleRespuesta>> listarRespuestas(@PageableDefault(size = 5) Pageable pageable){
        return ResponseEntity.ok(respuestaService.listarRespuestas(pageable));
    }

    //obtener una respuesta por id
    @Operation(summary = "Obtener una respuesta por id")
    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleRespuesta> obtenerRespuesta(@PathVariable Long id){
        DatosDetalleRespuesta respuestaDTO = respuestaService.obtenerRespuestaId(id);
        return ResponseEntity.ok(respuestaDTO);
    }

    //Publicar  una respuesta
    @Operation(summary = "Publicar una respuesta")
    @PostMapping("/publicar")
    public ResponseEntity<DatosDetalleRespuesta> publicarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datos,
                                                          UriComponentsBuilder uriBuilder){
        Respuesta respuesta = respuestaService.registrarRespuesta(datos);
        DatosDetalleRespuesta respuestaDTO = new DatosDetalleRespuesta(respuesta);
        URI uri = uriBuilder.path("respuesta/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(uri).body(respuestaDTO);
    }

    //editar una respuesta
    @Operation(summary = "Editar una respuesta")
    @PutMapping("/editar")
    @Transactional
    public ResponseEntity<DatosDetalleRespuesta> editarRespuesta(@RequestBody @Valid DatosActualizarRespuesta datos){
        var nuevaRespuesta = respuestaService.editarDatos(datos);
        return ResponseEntity.ok(nuevaRespuesta);
    }

    //eliminar una respuesta

//    @DeleteMapping("/eliminar/{id}")
//    @Transactional
//    public ResponseEntity<Object> eliminarRespuesta(@PathVariable Long id){
//        respuestaService.eliminarRespuesta(id);
//        return ResponseEntity.ok().build();
//    }
}
