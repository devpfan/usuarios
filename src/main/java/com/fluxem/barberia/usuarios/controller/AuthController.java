package com.fluxem.barberia.usuarios.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.fluxem.barberia.usuarios.domain.entity.Rol;
import com.fluxem.barberia.usuarios.domain.entity.Usuario;
import com.fluxem.barberia.usuarios.dto.LoginRequest;
import com.fluxem.barberia.usuarios.dto.LoginResponse;
import com.fluxem.barberia.usuarios.repository.UsuarioRepository;
import com.fluxem.barberia.usuarios.security.JwtTokenProvider;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioRepository.findByCorreoConRoles(request.getCorreo())
                .orElseThrow(() -> new IllegalArgumentException("Correo inválido o inexistente"));

        if (!passwordEncoder.matches(request.getContrasena(), usuario.getContrasenaHash())) {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }

        String token = jwtTokenProvider.generarToken(usuario.getCorreo());

        // Copia segura de roles
        Set<String> roles = new HashSet<>();
        for (Rol rol : usuario.getRoles()) {
            roles.add(rol.getNombre());
        }

        return new LoginResponse(token, usuario.getCorreo(), roles);
    }
}
