package com.duoc.gamer.dto;

import com.duoc.gamer.enums.UserRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UsuarioDTO {

    @NotNull(message = "El campo id no puede estar vacío")
    @Positive(message = "El campo id debe ser un número positivo")
    private Long id;

    @NotNull(message = "El campo username no puede estar vacío")
    @Size(min = 2, max = 20, message = "El campo nombre debe tener entre 2 y 20 caracteres")
    private String username;

    @NotNull(message = "El campo password no puede estar vacío")
    @Size(min = 2, max = 20, message = "El campo password debe tener entre 2 y 20 caracteres")
    private String password;

    @NotNull(message = "El campo email no puede estar vacío")
    private String email;

    @NotNull(message = "El campo nombre no puede estar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El campo nombre solo puede contener letras")
    private String nombre;

    @NotNull(message = "El campo apellidoPaterno no puede estar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El campo apellidoPaterno solo puede contener letras")
    private String apellidoPaterno;

    @NotNull(message = "El campo apellidoMaterno no puede estar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El campo apellidoMaterno solo puede contener letras")
    private String apellidoMaterno;

    @NotNull(message = "El campo fechaNacimiento no puede estar vacío")
    private LocalDate fechaNacimiento;

    @NotNull(message = "El campo role no puede estar vacío")
    private UserRole role;

    @NotNull(message = "El campo isLoggedIn no puede estar vacío")
    private boolean isLoggedIn;

    @NotNull(message = "El campo fechaCreacion no puede estar vacío")
    private LocalDate fechaCreacion;
}
