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
                .fechaInicio(eventoEntity.getFechaInicio())
                .organizadores(eventoEntity.getOrganizadores())
                .servicios(eventoEntity.getServicios())
                .expositores(eventoEntity.getExpositores())
                .premios(eventoEntity.getPremios())
                .build();
    }

    public EventoEntity eventoDtoToEntity(EventoDTO eventoDTO) {
        return EventoEntity.builder()
                .idEvento(eventoDTO.getIdEvento())
                .titulo(eventoDTO.getTitulo())
                .descripcion(eventoDTO.getDescripcion())
                .fechaInicio(eventoDTO.getFechaInicio())
                .organizadores(eventoDTO.getOrganizadores())
                .servicios(eventoDTO.getServicios())
                .expositores(eventoDTO.getExpositores())
                .premios(eventoDTO.getPremios())
                .build();
    }
}
