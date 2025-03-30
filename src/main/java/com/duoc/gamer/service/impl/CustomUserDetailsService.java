package com.duoc.gamer.service.impl;

import com.duoc.gamer.entities.UsuarioEntity;
import com.duoc.gamer.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UsuarioEntity> optionalUsuarioEntity = usuarioRepository.findByEmail(email);
        if (!optionalUsuarioEntity.isPresent()) {
            throw new UsernameNotFoundException("No se encontró usuario con email: " + email);
        }
        UsuarioEntity usuarioEntity = optionalUsuarioEntity.get();
        return User.builder()
                .username(usuarioEntity.getEmail())
                .password(usuarioEntity.getPassword())
                .roles(String.valueOf(usuarioEntity.getRole()))
                .build();
    }
}