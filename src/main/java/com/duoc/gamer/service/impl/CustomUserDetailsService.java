package com.duoc.gamer.service.impl;

import com.duoc.gamer.dto.UsuarioDTO;
import com.duoc.gamer.entities.UsuarioEntity;
import com.duoc.gamer.mapper.UsuarioMapper;
import com.duoc.gamer.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioEntity usuarioEntity = usuarioRepository.findByEmail(email);
        if (usuarioEntity == null) {
            throw new UsernameNotFoundException("No se encontró usuario con email: " + email);
        }
        return User.builder()
                .username(usuarioEntity.getEmail())
                .password(usuarioEntity.getPassword())
                .roles(String.valueOf(usuarioEntity.getRole()))
                .build();
    }
}