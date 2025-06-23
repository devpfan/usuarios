package com.fluxem.barberia.usuarios.domain.entity;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombre;

    private String correo;

    private String telefono;

    @Column(name = "contraseña_hash")
    private String contrasenaHash;

    private String estado;

    @Column(name = "fecha_creacion")
    private ZonedDateTime fechaCreacion = ZonedDateTime.now();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuario_rol",
        joinColumns = @JoinColumn(name = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    private Set<Rol> roles = new HashSet<>();

 }

