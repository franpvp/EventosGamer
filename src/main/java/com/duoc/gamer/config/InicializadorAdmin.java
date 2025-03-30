package com.duoc.gamer.config;

import com.duoc.gamer.dto.UsuarioDTO;
import com.duoc.gamer.entities.UsuarioEntity;
import com.duoc.gamer.enums.UserRole;
import com.duoc.gamer.mapper.UsuarioMapper;
import com.duoc.gamer.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;


@Slf4j
@Configuration
public class InicializadorAdmin {

    @Bean
    public CommandLineRunner initAdmin(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder) {
        return args -> {
            if (usuarioRepository.findByEmail("admin@gmail.com") == null) {
                // Construir el DTO para el admin
                UsuarioDTO adminDTO = UsuarioDTO.builder()
                        .username("admin")
                        .email("admin@gmail.com")
                        .nombre("Administrador")
                        .apellidoPaterno("Default")
                        .apellidoMaterno("Default")
                        .fechaNacimiento(LocalDate.of(2000, 1, 1))
                        .password("admin")
                        .build();

                String encodedPassword = passwordEncoder.encode(adminDTO.getPassword());
                adminDTO.setPassword(encodedPassword);

                UsuarioEntity adminEntity = usuarioMapper.usuarioDtoToEntity(adminDTO);
                adminEntity.setRole(UserRole.ADMIN);
                usuarioRepository.save(adminEntity);

                log.info("Usuario admin creado correctamente.");
            } else {
                log.info("El usuario admin ya existe.");
            }
        };
    }
}
