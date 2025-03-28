package com.duoc.gamer.entities;

import com.duoc.gamer.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "password")
    private String email;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "role")
    private UserRole role;

    @Column(name = "isLoggedIn")
    private boolean isLoggedIn;

    @Column(name = "fechaCreacion")
    private String fechaCreacion;


}
