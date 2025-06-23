package com.fluxem.barberia.usuarios.dto;

import java.util.Set;

import lombok.Data;

@Data
public class LoginResponse {

    private String token;
    private String correo;
    private Set<String> roles;

}
