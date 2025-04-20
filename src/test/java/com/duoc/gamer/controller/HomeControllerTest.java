package com.duoc.gamer.controller;

import com.duoc.gamer.dto.EventoDTO;
import com.duoc.gamer.security.JwtTokenUtil;
import com.duoc.gamer.service.EventoService;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    private EventoDTO eventoDTO;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private UserDetails userDetails;

    @Mock
    private Model model;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private EventoService eventoService;

    @InjectMocks
    private HomeController homeController;

    @BeforeEach
    void setUp() {
    }

    @Test
    void homeTest() {

        String resultado = homeController.home("name", model);

        assertNotNull(resultado);
        assertEquals("home", resultado);

    }

    @Test
    void crearEventoUsuarioTest() {

        String resultado = homeController.crearEventoUsuario(eventoDTO, bindingResult, userDetails, httpServletResponse);

        assertNotNull(resultado);
        assertEquals("redirect:/home", resultado);

        verify(jwtTokenUtil, times(1)).generateToken(userDetails);
        verify(eventoService, times(1)).crearEvento(eventoDTO);

    }

    @Test
    void crearEventoUsuarioHasErrorTest() {

        when(bindingResult.hasErrors()).thenReturn(true);

        String resultado = homeController.crearEventoUsuario(eventoDTO, bindingResult, userDetails, httpServletResponse);

        assertNotNull(resultado);
        assertEquals("home", resultado);


    }
}