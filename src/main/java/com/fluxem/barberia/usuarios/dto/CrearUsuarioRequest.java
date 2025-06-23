package com.fluxem.barberia.usuarios.dto;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CrearUsuarioRequest {

    @NotBlank
    private String nombre;

    @Email
    @NotBlank
    private String correo;

    private String telefono;

    @NotBlank
    private String contrasena;

    private Set<Long> roles;

}
