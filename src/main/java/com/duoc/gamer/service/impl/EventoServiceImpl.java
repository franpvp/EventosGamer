package com.duoc.gamer.service.impl;

import com.duoc.gamer.dto.EventoDTO;
import com.duoc.gamer.entities.EventoEntity;
import com.duoc.gamer.mapper.EventoMapper;
import com.duoc.gamer.repository.EventosRepository;
import com.duoc.gamer.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoServiceImpl implements EventoService {

    private final EventosRepository eventosRepository;

    @Autowired
    private EventoMapper eventoMapper;

    public EventoServiceImpl(EventosRepository eventoRepository) {
        this.eventosRepository = eventoRepository;
    }

    @Override
    public List<EventoDTO> getAllEventos() {
        List<EventoEntity> eventos = eventosRepository.findAll();
        return eventos.stream()
                .map(eventoMapper::eventoEntityToDto).toList();
    }

    @Override
    public EventoDTO getEventoById(Long id) {
        return null;
    }

    @Override
    public EventoDTO crearEvento(EventoDTO eventoDTO) {
        return null;
    }

    @Override
    public EventoDTO modificarEvento(EventoDTO eventoDTO) {
        return null;
    }

    @Override
    public void eliminarEventoById(Long id) {

    }


}
