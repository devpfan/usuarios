package com.fluxem.barberia.usuarios.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fluxem.barberia.usuarios.domain.entity.Rol;
import com.fluxem.barberia.usuarios.repository.RolRepository;
import com.fluxem.barberia.usuarios.service.RolService;

public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }

    @Override
    public Rol obtenerPorNombre(String nombre) {
        return rolRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + nombre));
    }

}
