package com.duoc.gamer.dto;

import com.duoc.gamer.entities.UsuarioEntity;
import com.duoc.gamer.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDTOTest {

    private static final Long ID = 1L;
    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";
    private static final String EMAIL = "EMAIL";
    private static final String NOMBRE = "NOMBRE";
    private static final String APELLIDO_PATERNO = "APELLIDO_PATERNO";
    private static final String APELLIDO_MATERNO = "APELLIDO_MATERNO";
    private static final LocalDate FECHA_NACIMIENTO = LocalDate.of(2025, 12, 12);
    private static final UserRole ROLE = UserRole.USER;

    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp() {

        usuarioDTO = UsuarioDTO.builder()
                .id(ID)
                .username(USERNAME)
                .password(PASSWORD)
                .email(EMAIL)
                .nombre(NOMBRE)
                .apellidoPaterno(APELLIDO_PATERNO)
                .apellidoMaterno(APELLIDO_MATERNO)
                .fechaNacimiento(FECHA_NACIMIENTO)
                .role(ROLE)
                .build();

    }

    @Test
    public void usuarioDtoTest() {

        assertEquals(ID, usuarioDTO.getId());
        assertEquals(USERNAME, usuarioDTO.getUsername());
        assertEquals(PASSWORD, usuarioDTO.getPassword());
        assertEquals(EMAIL, usuarioDTO.getEmail());
        assertEquals(NOMBRE, usuarioDTO.getNombre());
        assertEquals(APELLIDO_PATERNO, usuarioDTO.getApellidoPaterno());
        assertEquals(APELLIDO_MATERNO, usuarioDTO.getApellidoMaterno());
        assertEquals(FECHA_NACIMIENTO, usuarioDTO.getFechaNacimiento());
        assertEquals(ROLE, usuarioDTO.getRole());
    }

    @Test
    public void usuarioDtoSetterTest() {

        usuarioDTO.setId(ID);
        usuarioDTO.setUsername(USERNAME);
        usuarioDTO.setPassword(PASSWORD);
        usuarioDTO.setEmail(EMAIL);
        usuarioDTO.setNombre(NOMBRE);
        usuarioDTO.setApellidoPaterno(APELLIDO_PATERNO);
        usuarioDTO.setApellidoMaterno(APELLIDO_MATERNO);
        usuarioDTO.setFechaNacimiento(FECHA_NACIMIENTO);
        usuarioDTO.setRole(ROLE);

    }

    @Test
    public void usuarioDtoNoArgsTest() {

        UsuarioDTO dto = new UsuarioDTO();

        assertNotNull(dto);

    }
}