package com.duoc.gamer.controller;

import com.duoc.gamer.dto.UsuarioDTO;
import com.duoc.gamer.security.JwtTokenUtil;
import com.duoc.gamer.service.UsuarioService;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminUsuarioControllerTest {

    private static final Long ID = 1L;

    private UsuarioDTO usuarioDTO;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private Model model;

    @Mock
    private UserDetails userDetails;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @InjectMocks
    private AdminUsuarioController adminUsuarioController;

    @BeforeEach
    void setUp() {
        usuarioDTO = UsuarioDTO.builder()
                .id(ID)
                .build();

    }

    @Test
    public void listarUsuariosTest() {

        when(usuarioService.getAllUsuarios()).thenReturn(List.of(usuarioDTO));

        String resultado = adminUsuarioController.listarUsuarios(userDetails, model);

        assertNotNull(resultado);

        verify(usuarioService, times(1)).getAllUsuarios();

    }

    @Test
    public void listarUsuariosUserDetailsNullTest() {

        when(usuarioService.getAllUsuarios()).thenReturn(List.of(usuarioDTO));

        String resultado = adminUsuarioController.listarUsuarios(null, model);

        assertNotNull(resultado);

        verify(usuarioService, times(1)).getAllUsuarios();

    }

    @Test
    public void agregarUsuarioTest() {

        String resultado = adminUsuarioController.agregarUsuario(usuarioDTO, userDetails, bindingResult, httpServletResponse);

        assertNotNull(resultado);

        verify(usuarioService, times(1)).createUsuario(usuarioDTO);

    }

    @Test
    public void agregarUsuarioHasErrorTest() {

        when(bindingResult.hasErrors()).thenReturn(true);

        String resultado = adminUsuarioController.agregarUsuario(usuarioDTO, userDetails, bindingResult, httpServletResponse);

        assertNotNull(resultado);

        verify(bindingResult, times(1)).hasErrors();

    }
}