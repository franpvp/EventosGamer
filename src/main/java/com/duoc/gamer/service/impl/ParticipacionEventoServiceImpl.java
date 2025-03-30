package com.duoc.gamer.service.impl;

import com.duoc.gamer.dto.ParticipacionEventoDTO;
import com.duoc.gamer.entities.ParticipacionEventoEntity;
import com.duoc.gamer.repository.ParticipacionEventoRepository;
import com.duoc.gamer.service.ParticipacionEventoService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class ParticipacionEventoServiceImpl implements ParticipacionEventoService {

    private final ParticipacionEventoRepository participacionEventoRepository;

    public ParticipacionEventoServiceImpl(ParticipacionEventoRepository participacionEventoRepository) {
        this.participacionEventoRepository = participacionEventoRepository;
    }

    @Override
    public List<ParticipacionEventoDTO> getParticipacionEventos() {
        return List.of();
    }

    @Override
    public ParticipacionEventoDTO getParticipacionEventosById(Long id) {
        return null;
    }

    @Override
    public ParticipacionEventoDTO crearParticipacionEvento(ParticipacionEventoDTO participacionEventoDTO) {
        return null;
    }

    @Override
    public ParticipacionEventoDTO modificarParticipacionEventos(ParticipacionEventoDTO participacionEventoDTO) {
        return null;
    }

    @Override
    public void eliminarParticipacionEventosById(Long id) {

    }

    @Override
    public void registrarParticipacion(Long idUsuario, Long idEvento, LocalDate fechaInscripcion) {
        ParticipacionEventoEntity participacion = new ParticipacionEventoEntity();
        participacion.setIdUsuario(idUsuario);
        participacion.setIdEvento(idEvento);
        // Convertir LocalDate a java.sql.Date (o usar LocalDate directamente si tu entidad está preparada para ello)
        participacion.setFechaInscripcion(Date.valueOf(fechaInscripcion).toLocalDate());

        participacionEventoRepository.save(participacion);
    }
}
