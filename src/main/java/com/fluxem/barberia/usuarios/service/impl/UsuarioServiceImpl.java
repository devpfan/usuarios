package com.fluxem.barberia.usuarios.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fluxem.barberia.usuarios.domain.entity.Rol;
import com.fluxem.barberia.usuarios.domain.entity.Usuario;
import com.fluxem.barberia.usuarios.dto.CrearUsuarioRequest;
import com.fluxem.barberia.usuarios.dto.UsuarioDTO;
import com.fluxem.barberia.usuarios.repository.RolRepository;
import com.fluxem.barberia.usuarios.repository.UsuarioRepository;
import com.fluxem.barberia.usuarios.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UsuarioDTO crearUsuario(CrearUsuarioRequest request) {
        if (usuarioRepository.existsByCorreo(request.getCorreo())) {
            throw new RuntimeException("Ya existe un usuario con ese correo");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        usuario.setTelefono(request.getTelefono());
        usuario.setContrasenaHash(passwordEncoder.encode(request.getContrasena()));
        usuario.setEstado("ACTIVO");

        Set<Rol> roles = request.getRoles().stream()
            .map(idRol -> rolRepository.findById(idRol)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + idRol)))
            .collect(Collectors.toSet());

        usuario.setRoles(roles);
        Usuario guardado = usuarioRepository.save(usuario);

        return convertirADTO(guardado);
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(this::convertirADTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO convertirADTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombre(usuario.getNombre());
        dto.setCorreo(usuario.getCorreo());
        dto.setTelefono(usuario.getTelefono());
        dto.setEstado(usuario.getEstado());
        dto.setFechaCreacion(usuario.getFechaCreacion());
        dto.setRoles(usuario.getRoles().stream()
                .map(Rol::getNombre)
                .collect(Collectors.toSet()));
        return dto;
    }

}
