package com.duoc.gamer.service.impl;

import com.duoc.gamer.dto.UsuarioDTO;
import com.duoc.gamer.entities.UsuarioEntity;
import com.duoc.gamer.exceptions.UsuarioBadRequestException;
import com.duoc.gamer.exceptions.UsuarioNotFoundException;
import com.duoc.gamer.mapper.UsuarioMapper;
import com.duoc.gamer.repository.UsuarioRepository;
import com.duoc.gamer.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

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
        return usuarioRepository.findById(idUsuario)
                .map(usuarioMapper::usuarioEntityToDto)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado con id: " + idUsuario));
    }

    @Override
    public UsuarioDTO findUsuarioByUsername(String username) {
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

        return usuarioRepository.findById(usuarioDTO.getId())
                .map(usuarioEntity -> {
                    usuarioEntity.setId(usuarioDTO.getId());
                    usuarioEntity.setUsername(usuarioDTO.getUsername());
                    usuarioEntity.setEmail(usuarioDTO.getEmail());
                    usuarioEntity.setNombre(usuarioDTO.getNombre());
                    usuarioEntity.setApellidoPaterno(usuarioDTO.getApellidoPaterno());
                    usuarioEntity.setApellidoMaterno(usuarioDTO.getApellidoMaterno());
                    usuarioEntity.setFechaNacimiento(usuarioDTO.getFechaNacimiento());
                    usuarioEntity.setRole(usuarioDTO.getRole());

                    UsuarioEntity usuarioEntityModificado = usuarioRepository.save(usuarioEntity);
                    return usuarioMapper.usuarioEntityToDto(usuarioEntityModificado);
                })
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado con ID: " + usuarioDTO));
    }

    @Override
    public void eliminarUsuarioById(Long idUsuario) {
        if (idUsuario < 0) {
            throw new UsuarioBadRequestException("El ID del usuario no puede ser negativo: " + idUsuario);
        }

        if (!usuarioRepository.existsById(idUsuario)) {
            throw new UsuarioNotFoundException("Usuario no encontrado con ID: " + idUsuario);
        }

        usuarioRepository.deleteById(idUsuario);
    }
}
