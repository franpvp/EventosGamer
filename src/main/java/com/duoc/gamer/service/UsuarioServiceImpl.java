package com.duoc.gamer.service;

import com.duoc.gamer.dto.UsuarioDTO;
import com.duoc.gamer.entities.UsuarioEntity;
import com.duoc.gamer.mapper.UsuarioMapper;
import com.duoc.gamer.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }


    @Override
    public List<UsuarioDTO> getAllUsuarios() {
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuarioMapper::usuarioEntityToDto).toList();
    }

    @Override
    public UsuarioDTO getUsuarioById(Long idUsuario) {
        return null;
    }

    // Crea un nuevo usuario a partir del DTO recibido, lo guarda y retorna el DTO del usuario creado
    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {
        // Guardar el usuario usando JPA
        UsuarioEntity usuarioEntity = usuarioMapper.usuarioDtoToEntity(usuarioDTO);
        UsuarioEntity usuarioGuardado = usuarioRepository.save(usuarioEntity);
        // Convertir la entidad guardada a DTO y retornarla
        return usuarioMapper.usuarioEntityToDto(usuarioGuardado);
    }

    @Override
    public UsuarioDTO modificarUsuario(UsuarioDTO usuarioDTO) {
        return null;
    }

    @Override
    public void eliminarUsuarioById(Long idUsuario) {

    }
}
