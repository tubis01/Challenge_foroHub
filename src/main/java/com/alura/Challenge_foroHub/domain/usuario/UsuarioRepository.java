package com.alura.Challenge_foroHub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    UserDetails findByEmail(String email);

    boolean existsByNombre(String nombre);

    Usuario findByNombre(String nombre);
}
