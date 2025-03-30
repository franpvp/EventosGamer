package com.duoc.gamer.mapper;

import com.duoc.gamer.dto.UsuarioDTO;
import com.duoc.gamer.entities.UsuarioEntity;
import com.duoc.gamer.enums.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO usuarioEntityToDto(UsuarioEntity usuarioEntity) {
        return UsuarioDTO.builder()
                .id(usuarioEntity.getId())
                .username(usuarioEntity.getUsername())
                .password(usuarioEntity.getPassword())
                .email(usuarioEntity.getEmail())
                .role(usuarioEntity.getRole())
                .nombre(usuarioEntity.getNombre())
                .apellidoPaterno(usuarioEntity.getApellidoPaterno())
                .apellidoMaterno(usuarioEntity.getApellidoMaterno())
                .fechaNacimiento(usuarioEntity.getFechaNacimiento())
                .build();
    }

    public UsuarioEntity usuarioDtoToEntity(UsuarioDTO usuarioDTO) {
        return UsuarioEntity.builder()
                .username(usuarioDTO.getUsername())
                .password(usuarioDTO.getPassword())
                .email(usuarioDTO.getEmail())
                .role(usuarioDTO.getRole())
                .nombre(usuarioDTO.getNombre())
                .apellidoPaterno(usuarioDTO.getApellidoPaterno())
                .apellidoMaterno(usuarioDTO.getApellidoMaterno())
                .fechaNacimiento(usuarioDTO.getFechaNacimiento())
                .role(UserRole.ROLE_USER)
                .build();
    }
}
