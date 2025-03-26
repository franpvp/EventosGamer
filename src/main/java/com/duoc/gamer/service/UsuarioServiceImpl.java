package com.duoc.gamer.service;

import com.duoc.gamer.dto.UsuarioDTO;
import com.duoc.gamer.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioDTO> getAllUsuarios() {


        return List.of();
    }

    @Override
    public UsuarioDTO getUsuarioById(Long idUsuario) {
        return null;
    }

    @Override
    public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) {
        return null;
    }

    @Override
    public UsuarioDTO modificarUsuario(UsuarioDTO usuarioDTO) {
        return null;
    }

    @Override
    public void eliminarUsuarioById(Long idUsuario) {

    }
}
