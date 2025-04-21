package com.duoc.gamer.mapper;

import com.duoc.gamer.dto.ParticipacionEventoDTO;
import com.duoc.gamer.entities.ParticipacionEventoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ParticipacionEventoMapperTest {

    private static final Long ID_PART = 1L;
    private static final Long ID_EVENTO = 1L;
    private static final Long ID_USUARIO = 1L;
    private static final LocalDate FECHA_INSCRIPCION = LocalDate.of(2025, 12, 12);

    private ParticipacionEventoEntity participacionEventoEntity;

    private ParticipacionEventoDTO participacionEventoDTO;

    @InjectMocks
    private ParticipacionEventoMapper participacionEventoMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void participacionEventoEntityToDto() {

        participacionEventoEntity = ParticipacionEventoEntity.builder()
                .idParticipacionEvento(ID_PART)
                .idUsuario(ID_USUARIO)
                .idEvento(ID_EVENTO)
                .fechaInscripcion(FECHA_INSCRIPCION)
                .build();

        ParticipacionEventoDTO resultado = participacionEventoMapper.participacionEventoEntityToDto(participacionEventoEntity);

        assertNotNull(resultado);
        assertEquals(ID_PART, participacionEventoEntity.getIdParticipacionEvento());
        assertEquals(ID_EVENTO, participacionEventoEntity.getIdEvento());
        assertEquals(ID_USUARIO, participacionEventoEntity.getIdUsuario());
        assertEquals(FECHA_INSCRIPCION, participacionEventoEntity.getFechaInscripcion());

    }

    @Test
    void participacionEventoDtoToEntity() {

        participacionEventoDTO = ParticipacionEventoDTO.builder()
                .idParticipacionEvento(ID_PART)
                .idUsuario(ID_USUARIO)
                .idEvento(ID_EVENTO)
                .fechaInscripcion(FECHA_INSCRIPCION)
                .build();

        ParticipacionEventoEntity resultado = participacionEventoMapper.participacionEventoDtoToEntity(participacionEventoDTO);

        assertNotNull(resultado);
        assertEquals(ID_PART, participacionEventoDTO.getIdParticipacionEvento());
        assertEquals(ID_EVENTO, participacionEventoDTO.getIdEvento());
        assertEquals(ID_USUARIO, participacionEventoDTO.getIdUsuario());
        assertEquals(FECHA_INSCRIPCION, participacionEventoDTO.getFechaInscripcion());

    }
}