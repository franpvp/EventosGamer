package com.duoc.gamer.service.impl;

import com.duoc.gamer.dto.ParticipacionEventoDTO;
import com.duoc.gamer.entities.ParticipacionEventoEntity;
import com.duoc.gamer.mapper.ParticipacionEventoMapper;
import com.duoc.gamer.repository.ParticipacionEventoRepository;
import com.duoc.gamer.service.ParticipacionEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipacionEventoServiceImpl implements ParticipacionEventoService {

    private final ParticipacionEventoRepository participacionEventoRepository;
    private final ParticipacionEventoMapper participacionEventoMapper;

    @Autowired
    public ParticipacionEventoServiceImpl(ParticipacionEventoRepository participacionEventoRepository, ParticipacionEventoMapper participacionEventoMapper) {
        this.participacionEventoRepository = participacionEventoRepository;
        this.participacionEventoMapper = participacionEventoMapper;
    }

    @Override
    public List<ParticipacionEventoDTO> getParticipacionEventos() {

        List<ParticipacionEventoEntity> participacionEventoDTOList = participacionEventoRepository.findAll();

        return participacionEventoDTOList.stream()
                .map(participacionEventoMapper::participacionEventoEntityToDto).toList();
    }

    @Override
    public ParticipacionEventoDTO getParticipacionEventosById(Long id) {

        return participacionEventoRepository.findById(id).stream()
                .map(participacionEventoMapper::participacionEventoEntityToDto)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Participación no encontrada con id: " + id));
    }

    @Override
    public void registrarParticipacion(Long idUsuario, Long idEvento, LocalDate fechaInscripcion) {
        ParticipacionEventoEntity participacion = new ParticipacionEventoEntity();
        participacion.setIdUsuario(idUsuario);
        participacion.setIdEvento(idEvento);

        participacion.setFechaInscripcion(Date.valueOf(fechaInscripcion).toLocalDate());

        participacionEventoRepository.save(participacion);
    }
}
