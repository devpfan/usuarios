package com.fluxem.barberia.usuarios.service;

import java.util.List;

import com.fluxem.barberia.usuarios.domain.entity.Rol;

public interface RolService {

    List<Rol> listarRoles();
    Rol obtenerPorNombre(String nombre);

}
