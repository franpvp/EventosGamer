package com.duoc.gamer.controller;

import com.duoc.gamer.dto.UsuarioDTO;
import com.duoc.gamer.service.EventoService;
import com.duoc.gamer.service.ParticipacionEventoService;
import com.duoc.gamer.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TorneoControllerTest {

    private static final Long ID_EVENTO = 1L;
    private static final LocalDate FECHA_INSCRIPCION = LocalDate.now();

    @Mock
    private UsuarioDTO usuarioDTO;

    @Mock
    private RedirectAttributes redirectAttributes;

    @Mock
    private Model model;

    @Mock
    private UserDetails userDetails;

    @Mock
    private EventoService eventoService;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private ParticipacionEventoService participacionEventoService;

    @InjectMocks
    private TorneoController torneoController;

    @BeforeEach
    void setUp() {

    }

    @Test
    public void showTorneoPage() {

        String resultado = torneoController.showTorneoPage(userDetails, model);

        assertNotNull(resultado);
        assertEquals("torneo", resultado);

    }

    @Test
    public void showTorneoPageUserDetailsNullTest() {

        String resultado = torneoController.showTorneoPage(null, model);

        assertNotNull(resultado);
        assertEquals("torneo", resultado);

    }

    @Test
    public void participarEvento() {

        when(userDetails.getUsername()).thenReturn("USERNAME");
        when(usuarioService.findByEmail("USERNAME")).thenReturn(usuarioDTO);
        when(usuarioDTO.getId()).thenReturn(1L);

        doNothing().when(participacionEventoService).registrarParticipacion(1L, ID_EVENTO, FECHA_INSCRIPCION);

        String resultado = torneoController.participarEvento(ID_EVENTO, userDetails, redirectAttributes);

        assertNotNull(resultado);
        assertEquals("redirect:/torneo", resultado);

        verify(participacionEventoService, times(1)).registrarParticipacion(1L, ID_EVENTO, FECHA_INSCRIPCION);

    }
}