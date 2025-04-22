package com.duoc.gamer.service;

import com.duoc.gamer.dto.ParticipacionEventoDTO;

import java.time.LocalDate;
import java.util.List;

public interface ParticipacionEventoService {

    List<ParticipacionEventoDTO> getParticipacionEventos();

    ParticipacionEventoDTO getParticipacionEventosById(Long id);

    void registrarParticipacion(Long idUsuario, Long idEvento, LocalDate fechaInscripcion);
}
