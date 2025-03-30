package com.duoc.gamer.service.impl;

import com.duoc.gamer.dto.ParticipacionEventoDTO;
import com.duoc.gamer.service.ParticipacionEventoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipacionEventoServiceImpl implements ParticipacionEventoService {

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
}
