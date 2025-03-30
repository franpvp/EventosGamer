package com.duoc.gamer.mapper;

import com.duoc.gamer.dto.ParticipacionEventoDTO;
import com.duoc.gamer.entities.ParticipacionEventoEntity;
import org.springframework.stereotype.Component;

@Component
public class ParticipacionEventoMapper {

    public ParticipacionEventoDTO participacionEventoEntityToDto(ParticipacionEventoEntity participacionEventoEntity) {
        return ParticipacionEventoDTO.builder()
                .id(participacionEventoEntity.getId())
                .usuario(participacionEventoEntity.getUsuario())
                .evento(participacionEventoEntity.getEvento())
                .fechaInscripcion(participacionEventoEntity.getFechaInscripcion())
                .build();
    }

    public ParticipacionEventoEntity participacionEventoDtoToEntity(ParticipacionEventoDTO participacionEventoDTO) {
        return ParticipacionEventoEntity.builder()
                .id(participacionEventoDTO.getId())
                .usuario(participacionEventoDTO.getUsuario())
                .evento(participacionEventoDTO.getEvento())
                .fechaInscripcion(participacionEventoDTO.getFechaInscripcion())
                .build();
    }
}
