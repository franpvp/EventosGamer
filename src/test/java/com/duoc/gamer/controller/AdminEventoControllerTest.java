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

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminEventoControllerTest {

    @Mock
    private EventoDTO eventoDTO;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private Model model;

    @Mock
    private UserDetails userDetails;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private EventoService eventoService;

    @InjectMocks
    private AdminEventoController adminEventoController;

    @Test
    void mostrarEventosTest() {

        when(eventoService.getAllEventos()).thenReturn(List.of(eventoDTO));

        String resultado = adminEventoController.mostrarEventos(userDetails, model);

        assertNotNull(resultado);
    }

    @Test
    void mostrarEventosUserDetailNullTest() {

        when(eventoService.getAllEventos()).thenReturn(Collections.emptyList());

        String resultado = adminEventoController.mostrarEventos(null, model);

        assertNotNull(resultado);
    }

    @Test
    void processEventoTest() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String resultado = adminEventoController.processEvento(eventoDTO, bindingResult, userDetails, httpServletResponse);

        assertNotNull(resultado);
    }

    @Test
    void processEventoNoErrorTest() {

        when(bindingResult.hasErrors()).thenReturn(false);

        String resultado = adminEventoController.processEvento(eventoDTO, bindingResult, userDetails, httpServletResponse);

        assertNotNull(resultado);

        verify(jwtTokenUtil, times(1)).generateToken(userDetails);
        verify(eventoService, times(1)).crearEvento(eventoDTO);

    }
}