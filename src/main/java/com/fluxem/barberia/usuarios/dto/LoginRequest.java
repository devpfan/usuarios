package com.fluxem.barberia.usuarios.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String correo;
    private String contrasena;

}
