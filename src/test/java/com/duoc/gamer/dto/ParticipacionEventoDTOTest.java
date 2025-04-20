package com.duoc.gamer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ParticipacionEventoDTOTest {

    private static final Long ID_PARTICIPACION = 1L;
    private static final Long ID_USUARIO = 1L;
    private static final Long ID_EVENTO = 1L;
    private static final LocalDate FECHA_INSCRIPCION = LocalDate.of(2025,12,12);

    private ParticipacionEventoDTO participacionEventoDTO;

    @BeforeEach
    void setUp() {

        participacionEventoDTO = ParticipacionEventoDTO.builder()
                .idParticipacionEvento(ID_PARTICIPACION)
                .idUsuario(ID_USUARIO)
                .idEvento(ID_EVENTO)
                .fechaInscripcion(FECHA_INSCRIPCION)
                .build();
    }

    @Test
    public void participacionEventoDtoTest() {

        assertNotNull(participacionEventoDTO);
        assertEquals(ID_PARTICIPACION, participacionEventoDTO.getIdParticipacionEvento());
        assertEquals(ID_USUARIO, participacionEventoDTO.getIdUsuario());
        assertEquals(ID_EVENTO, participacionEventoDTO.getIdEvento());
        assertEquals(FECHA_INSCRIPCION, participacionEventoDTO.getFechaInscripcion());

    }

    @Test
    public void participacionEventoDtoSetterTest() {

        participacionEventoDTO.setIdParticipacionEvento(ID_PARTICIPACION);
        participacionEventoDTO.setIdUsuario(ID_USUARIO);
        participacionEventoDTO.setIdEvento(ID_EVENTO);
        participacionEventoDTO.setFechaInscripcion(FECHA_INSCRIPCION);

    }

    @Test
    public void participacionEventoDtoNoArgsTest() {

        ParticipacionEventoDTO participacionEventoDTO1 = new ParticipacionEventoDTO();

        assertNotNull(participacionEventoDTO1);

    }
}