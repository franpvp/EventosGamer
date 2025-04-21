package com.duoc.gamer.mapper;

import com.duoc.gamer.dto.EventoDTO;
import com.duoc.gamer.entities.EventoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventoMapperTest {

    private static final Long ID_EVENTO = 1L;
    private static final String TITULO = "TITULO";
    private static final String DESCRIPCION = "DESCRIPCION";
    private static final LocalDateTime FECHA_INICIO = LocalDateTime.of(2025, 12, 12, 12, 0);
    private static final String ORGANIZADORES = "ORGANIZADORES";
    private static final String SERVICIOS = "SERVICIOS";
    private static final String EXPOSITORES = "EXPOSITORES";
    private static final String PREMIOS = "PREMIOS";

    private EventoEntity eventoEntity;

    private EventoDTO eventoDTO;

    @InjectMocks
    private EventoMapper eventoMapper;

    @BeforeEach
    void setUp() {


    }

    @Test
    public void eventoEntityToDtoTest() {

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

        EventoDTO resultado = eventoMapper.eventoEntityToDto(eventoEntity);

        assertNotNull(resultado);
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
    public void eventoDtoToEntityTest() {

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

        EventoEntity resultado = eventoMapper.eventoDtoToEntity(eventoDTO);

        assertNotNull(resultado);
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
    public void eventoDtoToEntityNullTest() {

        EventoEntity resultado = eventoMapper.eventoDtoToEntity(null);

        assertNull(resultado);

    }
}