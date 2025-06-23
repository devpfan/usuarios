package com.fluxem.barberia.usuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fluxem.barberia.usuarios.domain.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    
    @Query("SELECT u FROM Usuario u JOIN FETCH u.roles WHERE u.correo = :correo")
    Optional<Usuario> findByCorreoConRoles(@Param("correo") String correo);
    boolean existsByCorreo(String correo);

}
