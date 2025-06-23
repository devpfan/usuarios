package com.fluxem.barberia.usuarios.dto;

import java.time.ZonedDateTime;
import java.util.Set;

import lombok.Data;

@Data
public class UsuarioDTO {

    private Long idUsuario;
    private String nombre;
    private String correo;
    private String telefono;
    private String estado;
    private ZonedDateTime fechaCreacion;
    private Set<String> roles;

}
