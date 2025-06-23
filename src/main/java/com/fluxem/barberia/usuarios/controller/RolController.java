package com.fluxem.barberia.usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fluxem.barberia.usuarios.domain.entity.Rol;
import com.fluxem.barberia.usuarios.service.RolService;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    public ResponseEntity<List<Rol>> listarRoles() {
        return ResponseEntity.ok(rolService.listarRoles());
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<Rol> obtenerPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(rolService.obtenerPorNombre(nombre));
    }

}
