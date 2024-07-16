package com.alura.Challenge_foroHub.controller;

import com.alura.Challenge_foroHub.domain.usuario.DatosAutenticacionUsuario;
import com.alura.Challenge_foroHub.domain.usuario.Usuario;
import com.alura.Challenge_foroHub.infra.security.DatosJWTtoke;
import com.alura.Challenge_foroHub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    public AutenticacionController(AuthenticationManager manager, TokenService tokenService) {
        this.authManager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity authenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){

        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.email(),datosAutenticacionUsuario.clave());

        Authentication usuarioAutenticado = authManager.authenticate(authToken);

        String JWTtoken = tokenService.generaToken((Usuario) usuarioAutenticado.getPrincipal());

        return  ResponseEntity.ok(new DatosJWTtoke(JWTtoken));
    }

}
