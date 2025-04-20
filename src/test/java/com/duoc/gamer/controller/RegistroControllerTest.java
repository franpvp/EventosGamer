package com.duoc.gamer.controller;

import com.duoc.gamer.dto.UsuarioDTO;
import com.duoc.gamer.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistroControllerTest {

    private UsuarioDTO usuarioDTO;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private Model model;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegistroController registroController;

    @BeforeEach
    void setUp() {

        usuarioDTO = UsuarioDTO.builder()
                .id(1L)
                .username("USERNAME")
                .build();
    }

    @Test
    public void showRegistroPage() {

        String resultado = registroController.showRegistroPage(model);

        assertNotNull(resultado);
        assertEquals("registro", resultado);

    }

    @Test
    public void processRegistroTest() {

        String resultado = registroController.processRegistro(usuarioDTO, bindingResult);

        assertNotNull(resultado);
        assertEquals("redirect:/login", resultado);

        verify(passwordEncoder, times(1)).encode(usuarioDTO.getPassword());
        verify(usuarioService, times(1)).createUsuario(usuarioDTO);

    }

    @Test
    public void processRegistroHasErrorTest() {

        when(bindingResult.hasErrors()).thenReturn(true);

        String resultado = registroController.processRegistro(usuarioDTO, bindingResult);

        assertNotNull(resultado);
        assertEquals("registro", resultado);

    }
}