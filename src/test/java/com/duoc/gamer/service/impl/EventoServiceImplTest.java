package com.duoc.gamer.service.impl;

import com.duoc.gamer.dto.EventoDTO;
import com.duoc.gamer.entities.EventoEntity;
import com.duoc.gamer.exceptions.EventoNotFoundException;
import com.duoc.gamer.mapper.EventoMapper;
import com.duoc.gamer.repository.EventosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventoServiceImplTest {

    private static final Long ID = 1L;

    private EventoEntity eventoEntity;

    private EventoDTO eventoDTO;

    @Mock
    private EventosRepository eventosRepository;

    @Mock
    private EventoMapper eventoMapper;

    @InjectMocks
    private EventoServiceImpl eventoService;

    @BeforeEach
    void setUp() {
        eventoEntity = EventoEntity.builder()
                .idEvento(ID)
                .build();

        eventoDTO = EventoDTO.builder()
                .idEvento(ID)
                .build();
    }

    @Test
    void getAllEventosTest() {

        when(eventosRepository.findAll()).thenReturn(List.of(eventoEntity));
        when(eventoMapper.eventoEntityToDto(eventoEntity)).thenReturn(eventoDTO);

        List<EventoDTO> resultado = eventoService.getAllEventos();

        assertNotNull(resultado);

        verify(eventosRepository, times(1)).findAll();
        verify(eventoMapper, times(1)).eventoEntityToDto(eventoEntity);

    }

    @Test
    void getEventoByIdTest() {

        when(eventosRepository.findById(ID)).thenReturn(Optional.of(eventoEntity));
        when(eventoMapper.eventoEntityToDto(eventoEntity)).thenReturn(eventoDTO);

        EventoDTO resultado = eventoService.getEventoById(ID);

        assertNotNull(resultado);

        verify(eventosRepository, times(1)).findById(ID);
        verify(eventoMapper, times(1)).eventoEntityToDto(eventoEntity);
    }

    @Test
    void getEventoByIdExceptionTest() {

        RuntimeException resultado = assertThrows(RuntimeException.class,
                () ->  eventoService.getEventoById(ID));

        assertNotNull(resultado);

        verify(eventosRepository, times(1)).findById(ID);
    }

    @Test
    void crearEventoTest() {

        when(eventoMapper.eventoDtoToEntity(eventoDTO)).thenReturn(eventoEntity);
        when(eventosRepository.save(eventoEntity)).thenReturn(eventoEntity);
        when(eventoMapper.eventoEntityToDto(eventoEntity)).thenReturn(eventoDTO);

        EventoDTO resultado = eventoService.crearEvento(eventoDTO);

        assertNotNull(resultado);

        verify(eventoMapper, times(1)).eventoDtoToEntity(eventoDTO);
        verify(eventosRepository, times(1)).save(eventoEntity);
        verify(eventoMapper, times(1)).eventoEntityToDto(eventoEntity);
    }

    @Test
    void modificarEventoTest() {

        when(eventoMapper.eventoDtoToEntity(eventoDTO)).thenReturn(eventoEntity);
        when(eventosRepository.save(eventoEntity)).thenReturn(eventoEntity);
        when(eventoMapper.eventoEntityToDto(eventoEntity)).thenReturn(eventoDTO);

        EventoDTO resultado = eventoService.modificarEvento(eventoDTO);

        assertNotNull(resultado);
        verify(eventoMapper, times(1)).eventoDtoToEntity(eventoDTO);
        verify(eventosRepository, times(1)).save(eventoEntity);
        verify(eventoMapper, times(1)).eventoEntityToDto(eventoEntity);
    }

    @Test
    void eliminarEventoByIdTest() {

        when(eventosRepository.existsById(ID)).thenReturn(true);
        doNothing().when(eventosRepository).deleteById(ID);

        eventoService.eliminarEventoById(ID);

        verify(eventosRepository, times(1)).existsById(ID);
        verify(eventosRepository, times(1)).deleteById(ID);

    }

    @Test
    void eliminarEventoByIdExceptionTest() {

        when(eventosRepository.existsById(ID)).thenReturn(false);

        assertThrows(EventoNotFoundException.class,
                () -> eventoService.eliminarEventoById(ID));

        verify(eventosRepository, times(1)).existsById(ID);
        verify(eventosRepository, never()).deleteById(ID);

    }
}