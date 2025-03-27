package com.duoc.gamer.mapper;

import com.duoc.gamer.dto.EventoDTO;
import com.duoc.gamer.entities.EventoEntity;
import org.springframework.stereotype.Component;

@Component
public class EventoMapper {

    public EventoDTO eventoEntityToDto(EventoEntity eventoEntity) {
        return EventoDTO.builder()
                .idEvento(eventoEntity.getIdEvento())
                .titulo(eventoEntity.getTitulo())
                .descripcion(eventoEntity.getDescripcion())
                .fechaInicioEvento(eventoEntity.getFechaInicioEvento())
                .fechaFinEvento(eventoEntity.getFechaFinEvento())
                .creacionEvento(eventoEntity.getCreacionEvento())
                .build();
    }

    public EventoEntity eventoDtoToEntity(EventoDTO eventoDTO) {
        return EventoEntity.builder()
                .idEvento(eventoDTO.getIdEvento())
                .titulo(eventoDTO.getTitulo())
                .descripcion(eventoDTO.getDescripcion())
                .fechaInicioEvento(eventoDTO.getFechaInicioEvento())
                .fechaFinEvento(eventoDTO.getFechaFinEvento())
                .creacionEvento(eventoDTO.getCreacionEvento())
                .build();
    }
}
