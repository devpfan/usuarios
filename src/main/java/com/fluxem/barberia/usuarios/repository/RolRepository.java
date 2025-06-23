package com.fluxem.barberia.usuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fluxem.barberia.usuarios.domain.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Long>{

    Optional<Rol> findByNombre(String nombre);

}
