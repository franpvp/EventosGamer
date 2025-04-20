package com.duoc.gamer.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EventoEntityTest {

    private static final Long ID_EVENTO = 1L;
    private static final String TITULO = "TITULO";
    private static final String DESCRIPCION = "DESCRIPCION";
    private static final LocalDateTime FECHA_INICIO = LocalDateTime.of(2025, 12, 12, 12, 0);
    private static final String ORGANIZADORES = "ORGANIZADORES";
    private static final String SERVICIOS = "SERVICIOS";
    private static final String EXPOSITORES = "EXPOSITORES";
    private static final String PREMIOS = "PREMIOS";


    private EventoEntity eventoEntity;

    @BeforeEach
    void setUp() {

        eventoEntity = EventoEntity.builder()
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
    public void eventoEntityTest() {

        assertNotNull(eventoEntity);
        assertEquals(ID_EVENTO, eventoEntity.getIdEvento());
        assertEquals(TITULO, eventoEntity.getTitulo());
        assertEquals(DESCRIPCION, eventoEntity.getDescripcion());
        assertEquals(FECHA_INICIO, eventoEntity.getFechaInicio());
        assertEquals(ORGANIZADORES, eventoEntity.getOrganizadores());
        assertEquals(SERVICIOS, eventoEntity.getServicios());
        assertEquals(EXPOSITORES, eventoEntity.getExpositores());
        assertEquals(PREMIOS, eventoEntity.getPremios());
    }

    @Test
    public void eventoEntitySetterTest() {

        eventoEntity.setIdEvento(ID_EVENTO);
        eventoEntity.setTitulo(TITULO);
        eventoEntity.setDescripcion(DESCRIPCION);
        eventoEntity.setFechaInicio(FECHA_INICIO);
        eventoEntity.setOrganizadores(ORGANIZADORES);
        eventoEntity.setServicios(SERVICIOS);
        eventoEntity.setExpositores(EXPOSITORES);
        eventoEntity.setPremios(PREMIOS);

        assertNotNull(eventoEntity);
        assertEquals(ID_EVENTO, eventoEntity.getIdEvento());
        assertEquals(TITULO, eventoEntity.getTitulo());
        assertEquals(DESCRIPCION, eventoEntity.getDescripcion());
        assertEquals(FECHA_INICIO, eventoEntity.getFechaInicio());
        assertEquals(ORGANIZADORES, eventoEntity.getOrganizadores());
        assertEquals(SERVICIOS, eventoEntity.getServicios());
        assertEquals(EXPOSITORES, eventoEntity.getExpositores());
        assertEquals(PREMIOS, eventoEntity.getPremios());

    }

    @Test
    public void eventoEntityNoArgsTest() {

        EventoEntity entity = new EventoEntity();

        assertNotNull(entity);

    }

}