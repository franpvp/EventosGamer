package com.duoc.gamer.service;


import com.duoc.gamer.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {

    List<UsuarioDTO> getAllUsuarios();
    UsuarioDTO getUsuarioById(Long idUsuario);
    UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO modificarUsuario(UsuarioDTO usuarioDTO);
    void eliminarUsuarioById(Long idUsuario);

}
