package com.duoc.gamer.entities;

import com.duoc.gamer.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioEntityTest {

    private static final Long ID = 1L;
    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";
    private static final String EMAIL = "EMAIL";
    private static final String NOMBRE = "NOMBRE";
    private static final String APELLIDO_PATERNO = "APELLIDO_PATERNO";
    private static final String APELLIDO_MATERNO = "APELLIDO_MATERNO";
    private static final LocalDate FECHA_NACIMIENTO = LocalDate.of(2025, 12, 12);
    private static final UserRole ROLE = UserRole.USER;

    private UsuarioEntity usuarioEntity;

    @BeforeEach
    void setUp() {

        usuarioEntity = UsuarioEntity.builder()
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
    public void usuarioEntityTest() {

        assertEquals(ID, usuarioEntity.getId());
        assertEquals(USERNAME, usuarioEntity.getUsername());
        assertEquals(PASSWORD, usuarioEntity.getPassword());
        assertEquals(EMAIL, usuarioEntity.getEmail());
        assertEquals(NOMBRE, usuarioEntity.getNombre());
        assertEquals(APELLIDO_PATERNO, usuarioEntity.getApellidoPaterno());
        assertEquals(APELLIDO_MATERNO, usuarioEntity.getApellidoMaterno());
        assertEquals(FECHA_NACIMIENTO, usuarioEntity.getFechaNacimiento());
        assertEquals(ROLE, usuarioEntity.getRole());

    }

    @Test
    public void usuarioEntitySetterTest() {

        usuarioEntity.setId(ID);
        usuarioEntity.setUsername(USERNAME);
        usuarioEntity.setPassword(PASSWORD);
        usuarioEntity.setEmail(EMAIL);
        usuarioEntity.setNombre(NOMBRE);
        usuarioEntity.setApellidoPaterno(APELLIDO_PATERNO);
        usuarioEntity.setApellidoMaterno(APELLIDO_MATERNO);
        usuarioEntity.setFechaNacimiento(FECHA_NACIMIENTO);
        usuarioEntity.setRole(ROLE);

    }

    @Test
    public void usuarioEntityNoArgsTest() {

        UsuarioEntity entity = new UsuarioEntity();

        assertNotNull(entity);


    }
}