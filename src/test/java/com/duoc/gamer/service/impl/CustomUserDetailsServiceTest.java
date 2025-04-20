package com.duoc.gamer.service.impl;

import com.duoc.gamer.entities.UsuarioEntity;
import com.duoc.gamer.enums.UserRole;
import com.duoc.gamer.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    private static final Long ID = 1L;
    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD123";
    private static final UserRole ROLE = UserRole.USER;
    private static final String EMAIL = "test@gmail.com";

    private UsuarioEntity usuarioEntity;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    void setUp() {

        usuarioEntity = UsuarioEntity.builder()
                .id(ID)
                .username(USERNAME)
                .password(PASSWORD)
                .role(ROLE)
                .email(EMAIL)
                .build();
    }

    @Test
    public void loadUserByUsernameTest() {

        when(usuarioRepository.findByEmail(EMAIL)).thenReturn(Optional.of(usuarioEntity));

        UserDetails resultado = customUserDetailsService.loadUserByUsername(EMAIL);

        assertNotNull(resultado);

        verify(usuarioRepository, times(1)).findByEmail(EMAIL);

    }

    @Test
    public void loadUserByUsernameExceptionTest() {

        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class,
                () -> customUserDetailsService.loadUserByUsername(EMAIL));

        assertEquals("No se encontró usuario con email: " + EMAIL, exception.getMessage());

        verify(usuarioRepository, times(1)).findByEmail(EMAIL);

    }
}