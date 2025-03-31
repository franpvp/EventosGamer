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
    private final EventoMapper eventoMapper;

    @Autowired
    public EventoServiceImpl(EventosRepository eventosRepository, EventoMapper eventoMapper) {
        this.eventosRepository = eventosRepository;
        this.eventoMapper = eventoMapper;
    }

    @Override
    public List<EventoDTO> getAllEventos() {
        List<EventoEntity> eventos = eventosRepository.findAll();
        return eventos.stream()
                .map(eventoMapper::eventoEntityToDto).toList();
    }

    @Override
    public EventoDTO getEventoById(Long id) {
        EventoEntity eventoEntity = eventosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con id: " + id));
        return eventoMapper.eventoEntityToDto(eventoEntity);
    }

    @Override
    public EventoDTO crearEvento(EventoDTO eventoDTO) {
        EventoEntity eventoEntity = eventoMapper.eventoDtoToEntity(eventoDTO);
        EventoEntity eventoCreado = eventosRepository.save(eventoEntity);

        return eventoMapper.eventoEntityToDto(eventoCreado);
    }

    @Override
    public EventoDTO modificarEvento(EventoDTO eventoDTO) {
        // Verificamos que el evento exista
        EventoEntity eventoExistente = eventosRepository.findById(eventoDTO.getIdEvento())
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con id: " + eventoDTO.getIdEvento()));
        // Mapeamos el DTO a entidad (puedes optar por actualizar manualmente los campos si lo requieres)
        EventoEntity eventoAActualizar = eventoMapper.eventoDtoToEntity(eventoDTO);
        EventoEntity eventoActualizado = eventosRepository.save(eventoAActualizar);
        return eventoMapper.eventoEntityToDto(eventoActualizado);
    }

    @Override
    public void eliminarEventoById(Long id) {
        if (!eventosRepository.existsById(id)) {
            throw new RuntimeException("Evento no encontrado con id: " + id);
        }
        eventosRepository.deleteById(id);
    }


}
