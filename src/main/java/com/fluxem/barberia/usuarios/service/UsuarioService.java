package com.fluxem.barberia.usuarios.service;

import java.util.List;

import com.fluxem.barberia.usuarios.dto.CrearUsuarioRequest;
import com.fluxem.barberia.usuarios.dto.UsuarioDTO;

public interface UsuarioService {

    UsuarioDTO crearUsuario(CrearUsuarioRequest request);
    UsuarioDTO obtenerUsuarioPorId(Long id);
    List<UsuarioDTO> listarUsuarios();
    void eliminarUsuario(Long id);

}
