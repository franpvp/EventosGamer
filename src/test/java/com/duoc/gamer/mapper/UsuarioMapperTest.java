package com.duoc.gamer.mapper;

import com.duoc.gamer.dto.UsuarioDTO;
import com.duoc.gamer.entities.UsuarioEntity;
import com.duoc.gamer.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioMapperTest {

    private static final Long ID = 1L;
    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";
    private static final String EMAIL = "EMAIL";
    private static final String NOMBRE = "NOMBRE";
    private static final String APELLIDO_PATERNO = "APELLIDO_PATERNO";
    private static final String APELLIDO_MATERNO = "APELLIDO_MATERNO";
    private static final LocalDate FECHA_NACIMIENTO = LocalDate.of(2025, 12, 12);
    private static final UserRole ROLE = UserRole.USER;

    @Mock
    private UsuarioEntity usuarioEntity;

    @Mock
    private UsuarioDTO usuarioDTO;

    @InjectMocks
    private UsuarioMapper usuarioMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void usuarioEntityToDto() {

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

        UsuarioDTO resultado = usuarioMapper.usuarioEntityToDto(usuarioEntity);

        assertNotNull(resultado);
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
    void usuarioDtoToEntity() {

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

        UsuarioEntity resultado = usuarioMapper.usuarioDtoToEntity(usuarioDTO);

        assertNotNull(resultado);
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
}