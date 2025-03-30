package com.duoc.gamer.mapper;

import com.duoc.gamer.dto.ParticipacionEventoDTO;
import com.duoc.gamer.entities.ParticipacionEventoEntity;
import org.springframework.stereotype.Component;

@Component
public class ParticipacionEventoMapper {

    public ParticipacionEventoDTO participacionEventoEntityToDto(ParticipacionEventoEntity participacionEventoEntity) {
        return ParticipacionEventoDTO.builder()
                .idParticipacionEvento(participacionEventoEntity.getIdEvento())
                .idUsuario(participacionEventoEntity.getIdUsuario())
                .idEvento(participacionEventoEntity.getIdEvento())
                .fechaInscripcion(participacionEventoEntity.getFechaInscripcion())
                .build();
    }

    public ParticipacionEventoEntity participacionEventoDtoToEntity(ParticipacionEventoDTO participacionEventoDTO) {
        return ParticipacionEventoEntity.builder()
                .idParticipacionEvento(participacionEventoDTO.getIdParticipacionEvento())
                .idUsuario(participacionEventoDTO.getIdUsuario())
                .idEvento(participacionEventoDTO.getIdEvento())
                .fechaInscripcion(participacionEventoDTO.getFechaInscripcion())
                .build();
    }
}
