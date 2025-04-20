package com.duoc.gamer.config;

import com.duoc.gamer.dto.UsuarioDTO;
import com.duoc.gamer.entities.UsuarioEntity;
import com.duoc.gamer.enums.UserRole;
import com.duoc.gamer.mapper.UsuarioMapper;
import com.duoc.gamer.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InicializadorAdminTest {

    private static final String EMAIL = "admin@gmail.com";

    private UsuarioEntity usuarioEntity;

    private UsuarioDTO usuarioDTO;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private InicializadorAdmin inicializadorAdmin;

    @BeforeEach
    void setUp() {

        usuarioEntity = UsuarioEntity.builder()
                .id(1L)
                .username("admin")
                .email("admin@gmail.com")
                .build();

        usuarioDTO = UsuarioDTO.builder()
                .username("admin")
                .email("admin@gmail.com")
                .nombre("Administrador")
                .apellidoPaterno("Default")
                .apellidoMaterno("Default")
                .fechaNacimiento(LocalDate.of(2000, 1, 1))
                .password("admin")
                .build();
    }

    @Test
    void initAdmin() throws Exception {

        when(usuarioRepository.findByEmail(EMAIL)).thenReturn(Optional.empty());
        when(passwordEncoder.encode("admin")).thenReturn("encoded_password");
        when(usuarioMapper.usuarioDtoToEntity(any(UsuarioDTO.class))).thenReturn(usuarioEntity);

        CommandLineRunner resultado = inicializadorAdmin.initAdmin(usuarioRepository, usuarioMapper, passwordEncoder);

        resultado.run();

        assertNotNull(resultado);
        assertEquals(UserRole.ADMIN, usuarioEntity.getRole());

        verify(usuarioRepository, times(1)).save(usuarioEntity);

    }
}