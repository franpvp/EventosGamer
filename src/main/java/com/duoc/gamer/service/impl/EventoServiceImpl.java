package com.duoc.gamer.service.impl;

import com.duoc.gamer.dto.EventoDTO;
import com.duoc.gamer.service.EventoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoServiceImpl implements EventoService {
    @Override
    public List<EventoDTO> getEventos() {
        return List.of();
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
