package com.fluxem.barberia.usuarios.dto;

import java.util.Set;

import lombok.Data;

@Data
public class LoginResponse {

    private String token;
    private String correo;
    private Set<String> roles;

    public LoginResponse(String token, String correo, Set<String> roles) {
    this.token = token;
    this.correo = correo;
    this.roles = roles;
}

}
