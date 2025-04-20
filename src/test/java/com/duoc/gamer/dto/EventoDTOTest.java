package com.duoc.gamer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EventoDTOTest {

    private static final Long ID_EVENTO = 1L;
    private static final String TITULO = "TITULO";
    private static final String DESCRIPCION = "DESCRIPCION";
    private static final LocalDateTime FECHA_INICIO = LocalDateTime.of(2025, 12, 12, 12, 0);;
    private static final String ORGANIZADORES = "ORGANIZADORES";
    private static final String SERVICIOS = "SERVICIOS";
    private static final String EXPOSITORES = "EXPOSITORES";
    private static final String PREMIOS = "PREMIOS";

    private EventoDTO eventoDTO;

    @BeforeEach
    void setUp() {
        eventoDTO = EventoDTO.builder()
                .idEvento(ID_EVENTO)
                .titulo(TITULO)
                .descripcion(DESCRIPCION)
                .fechaInicio(FECHA_INICIO)
                .organizadores(ORGANIZADORES)
                .servicios(SERVICIOS)
                .expositores(EXPOSITORES)
                .premios(PREMIOS)
                .build();
    }

    @Test
    public void eventoDtoTest() {

        assertNotNull(eventoDTO);
        assertEquals(ID_EVENTO, eventoDTO.getIdEvento());
        assertEquals(TITULO, eventoDTO.getTitulo());
        assertEquals(DESCRIPCION, eventoDTO.getDescripcion());
        assertEquals(FECHA_INICIO, eventoDTO.getFechaInicio());
        assertEquals(ORGANIZADORES, eventoDTO.getOrganizadores());
        assertEquals(SERVICIOS, eventoDTO.getServicios());
        assertEquals(EXPOSITORES, eventoDTO.getExpositores());
        assertEquals(PREMIOS, eventoDTO.getPremios());

    }

    @Test
    public void eventoDtoSetterTest() {

        eventoDTO.setIdEvento(ID_EVENTO);
        eventoDTO.setTitulo(TITULO);
        eventoDTO.setDescripcion(DESCRIPCION);
        eventoDTO.setFechaInicio(FECHA_INICIO);
        eventoDTO.setOrganizadores(ORGANIZADORES);
        eventoDTO.setServicios(SERVICIOS);
        eventoDTO.setExpositores(EXPOSITORES);
        eventoDTO.setPremios(PREMIOS);

    }

    @Test
    public void eventoDtoNoArgsTest() {
        EventoDTO eventoDTO1 = new EventoDTO();

        assertNotNull(eventoDTO1);

    }
}