package com.duoc.gamer.mapper;

import com.duoc.gamer.dto.UsuarioDTO;
import com.duoc.gamer.entities.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO usuarioEntityToDto(UsuarioEntity usuarioEntity) {
        return UsuarioDTO.builder()
                .id(usuarioEntity.getId())
                .username(usuarioEntity.getUsername())
                .password(usuarioEntity.getPassword())
                .email(usuarioEntity.getEmail())
                .nombre(usuarioEntity.getNombre())
                .apellidoPaterno(usuarioEntity.getApellidoPaterno())
                .apellidoMaterno(usuarioEntity.getApellidoMaterno())
                .fechaNacimiento(usuarioEntity.getFechaNacimiento())
                .role(usuarioEntity.getRole())
                .build();
    }

    public UsuarioEntity usuarioDtoToEntity(UsuarioDTO usuarioDTO) {
        return UsuarioEntity.builder()
                .username(usuarioDTO.getUsername())
                .password(usuarioDTO.getPassword())
                .email(usuarioDTO.getEmail())
                .nombre(usuarioDTO.getNombre())
                .apellidoPaterno(usuarioDTO.getApellidoPaterno())
                .apellidoMaterno(usuarioDTO.getApellidoMaterno())
                .fechaNacimiento(usuarioDTO.getFechaNacimiento())
                .role(usuarioDTO.getRole())
                .build();
    }
}
