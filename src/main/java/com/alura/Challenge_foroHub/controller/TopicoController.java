package com.alura.Challenge_foroHub.controller;

import com.alura.Challenge_foroHub.domain.topico.*;
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
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    //listar todos los topicos
    @Operation(summary = "Listar todos los topicos")
    @GetMapping("/listar")
    public ResponseEntity<Page<DatosDetalleTopico>> lisarTopicos(@PageableDefault(size = 5) Pageable pageable){
        return ResponseEntity.ok(topicoService.listarTopicos(pageable));
    }

    //obtener un topico por id
    @Operation(summary = "Obtener un topico por id")
    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> obtenerTopico(@PathVariable Long id){
        Topico topico = topicoService.obtenerTopico(id);
        var respuesta = new DatosDetalleTopico(topico);
        return ResponseEntity.ok(respuesta);
    }

    //registrar topico
    @Operation(summary = "Registrar un topico")
    @PostMapping("/registrar")
    public ResponseEntity<DatosDetalleTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosTopico,
                                                           UriComponentsBuilder uriBuilder){
        Topico topico = topicoService.registrarTopico(datosTopico);
        var respuesta = new DatosDetalleTopico(topico);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(respuesta);
    }

    //modificar topico
    @Operation(summary = "Modificar un topico")
    @PutMapping("/actualizar/{id}")
    @Transactional
    public ResponseEntity<DatosDetalleTopico> actualizarTopico(@PathVariable Long id,
                                                            @RequestBody @Valid DatosActualizarTopico datos){
        var respuesta = topicoService.actualizarTopico(id, datos);
        return ResponseEntity.ok(respuesta);
    }

    //marcar topico como resuelto
    @Operation(summary = "Marcar un topico como resuelto")
    @PutMapping("/resuelto/{id}")
    @Transactional
    public ResponseEntity<DatosDetalleTopico> topicoResuelto(@PathVariable Long id){
        var respuesta = topicoService.topicoResuelto(id);
        return ResponseEntity.ok(respuesta);
    }

    //eliminar topico
    @Operation(summary = "Eliminar un topico")
    @DeleteMapping("/eliminar/{id}")
    @Transactional
    public ResponseEntity<Object> eliminacionTopico(@PathVariable Long id){
        topicoService.desactivarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
