package com.duoc.gamer.service;

import com.duoc.gamer.dto.EventoDTO;

import java.util.List;

public interface EventoService {

    List<EventoDTO> getEventos();
    EventoDTO getEventoById(Long id);
    EventoDTO crearEvento(EventoDTO eventoDTO);
    EventoDTO modificarEvento(EventoDTO eventoDTO);
    void eliminarEventoById(Long id);

}
