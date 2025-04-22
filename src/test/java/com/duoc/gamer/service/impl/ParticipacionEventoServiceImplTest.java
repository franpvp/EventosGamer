package com.duoc.gamer.service.impl;

import com.duoc.gamer.dto.ParticipacionEventoDTO;
import com.duoc.gamer.entities.ParticipacionEventoEntity;
import com.duoc.gamer.mapper.ParticipacionEventoMapper;
import com.duoc.gamer.repository.ParticipacionEventoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParticipacionEventoServiceImplTest {

    private static final Long ID_USUARIO = 1L;
    private static final Long ID_EVENTO = 1L;
    private static final LocalDate FECHA_INSCRIPCION = LocalDate.of(2025,10,10);

    private ParticipacionEventoEntity participacionEventoEntity;

    private ParticipacionEventoDTO participacionEventoDTO;

    @Mock
    private ParticipacionEventoMapper participacionEventoMapper;

    @Mock
    private ParticipacionEventoRepository participacionEventoRepository;

    @InjectMocks
    private ParticipacionEventoServiceImpl participacionEventoService;

    @BeforeEach
    void setUp() {

        participacionEventoEntity = ParticipacionEventoEntity.builder()
                .idUsuario(ID_USUARIO)
                .idEvento(ID_EVENTO)
                .fechaInscripcion(FECHA_INSCRIPCION)
                .build();

        participacionEventoDTO = ParticipacionEventoDTO.builder()
                .idUsuario(ID_USUARIO)
                .idEvento(ID_EVENTO)
                .fechaInscripcion(FECHA_INSCRIPCION)
                .build();
    }

    @Test
    public void getParticipacionEventosTest() {

        when(participacionEventoMapper.participacionEventoEntityToDto(participacionEventoEntity)).thenReturn(participacionEventoDTO);
        when(participacionEventoRepository.findAll()).thenReturn(List.of(participacionEventoEntity));

        List<ParticipacionEventoDTO> resultado = participacionEventoService.getParticipacionEventos();

        assertNotNull(resultado);

        verify(participacionEventoMapper, times(1)).participacionEventoEntityToDto(participacionEventoEntity);
        verify(participacionEventoRepository, times(1)).findAll();

    }

    @Test
    public void getParticipacionEventosByIdTest() {

        when(participacionEventoMapper.participacionEventoEntityToDto(participacionEventoEntity)).thenReturn(participacionEventoDTO);
        when(participacionEventoRepository.findById(anyLong())).thenReturn(Optional.of(participacionEventoEntity));

        ParticipacionEventoDTO resultado = participacionEventoService.getParticipacionEventosById(1L);

        assertNotNull(resultado);

        verify(participacionEventoMapper, times(1)).participacionEventoEntityToDto(participacionEventoEntity);
        verify(participacionEventoRepository, times(1)).findById(1L);

    }

    @Test
    public void registrarParticipacionTest() {

        when(participacionEventoRepository.save(any(ParticipacionEventoEntity.class))).thenReturn(participacionEventoEntity);

        participacionEventoService.registrarParticipacion(ID_USUARIO, ID_EVENTO, FECHA_INSCRIPCION);

    }
}