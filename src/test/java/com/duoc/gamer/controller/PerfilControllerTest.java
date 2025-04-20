package com.duoc.gamer.controller;

import com.duoc.gamer.dto.UsuarioDTO;
import com.duoc.gamer.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PerfilControllerTest {

    private static final Long ID = 1L;
    private static final String USERNAME = "USERNAME";

    private UsuarioDTO usuarioDTO;

    @Mock
    private Model model;

    @Mock
    private UserDetails userDetails;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private PerfilController perfilController;

    @BeforeEach
    void setUp() {

        usuarioDTO = UsuarioDTO.builder()
                .id(ID)
                .username(USERNAME)
                .build();
    }

    @Test
    public void showPerfilPageTest() {

        when(userDetails.getUsername()).thenReturn(USERNAME);

        String resultado = perfilController.showPerfilPage(userDetails, model);

        assertNotNull(resultado);
        assertEquals(USERNAME, userDetails.getUsername());
        assertEquals("perfil", resultado);

        verify(usuarioService, times(1)).findByEmail(USERNAME);

    }

    @Test
    public void showPerfilPageNullTest() {

        String resultado = perfilController.showPerfilPage(null, model);

        assertNotNull(resultado);
        assertEquals("redirect:/login", resultado);

    }
}