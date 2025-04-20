package com.duoc.gamer.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ParticipacionEventoEntityTest {

    private static final Long ID_PART = 1L;
    private static final Long ID_EVENTO = 1L;
    private static final Long ID_USUARIO = 1L;
    private static final LocalDate FECHA_INSCRIPCION = LocalDate.of(2025, 12, 12);

    private ParticipacionEventoEntity participacionEventoEntity;

    @BeforeEach
    void setUp() {

        participacionEventoEntity = ParticipacionEventoEntity.builder()
                .idParticipacionEvento(ID_PART)
                .idEvento(ID_EVENTO)
                .idUsuario(ID_USUARIO)
                .fechaInscripcion(FECHA_INSCRIPCION)
                .build();
    }

    @Test
    public void participacionEventoTest() {

        assertEquals(ID_PART, participacionEventoEntity.getIdParticipacionEvento());
        assertEquals(ID_EVENTO, participacionEventoEntity.getIdEvento());
        assertEquals(ID_USUARIO, participacionEventoEntity.getIdUsuario());
        assertEquals(FECHA_INSCRIPCION, participacionEventoEntity.getFechaInscripcion());

    }

    @Test
    public void participacionEventoSetterTest() {
        participacionEventoEntity.setIdParticipacionEvento(ID_PART);
        participacionEventoEntity.setIdEvento(ID_EVENTO);
        participacionEventoEntity.setIdUsuario(ID_USUARIO);
        participacionEventoEntity.setFechaInscripcion(FECHA_INSCRIPCION);
    }

    @Test
    public void participacionEventoNoArgsTest() {

        ParticipacionEventoEntity entity = new ParticipacionEventoEntity();

        assertNotNull(entity);

    }
}