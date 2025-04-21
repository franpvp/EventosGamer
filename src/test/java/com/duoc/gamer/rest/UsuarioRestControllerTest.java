package com.duoc.gamer.rest;

import com.duoc.gamer.dto.UsuarioDTO;
import com.duoc.gamer.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioRestControllerTest {

    private UsuarioDTO usuarioDTO;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioRestController usuarioRestController;

    @BeforeEach
    void setUp() {

        usuarioDTO = UsuarioDTO.builder()
                .id(1L)
                .build();
    }

    @Test
    void getAllUsuarios() {

        when(usuarioService.getAllUsuarios()).thenReturn(List.of(usuarioDTO));

        ResponseEntity<List<UsuarioDTO>> resultado =  usuarioRestController.getAllUsuarios();

        assertNotNull(resultado);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(List.of(usuarioDTO), resultado.getBody());

        verify(usuarioService, times(1)).getAllUsuarios();

    }

    @Test
    void createUsuario() {

        when(usuarioService.createUsuario(any(UsuarioDTO.class))).thenReturn(usuarioDTO);

        ResponseEntity<UsuarioDTO> resultado = usuarioRestController.createUsuario(usuarioDTO);

        assertNotNull(resultado);
        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
        assertEquals(usuarioDTO, resultado.getBody());

        verify(usuarioService, times(1)).createUsuario(usuarioDTO);

    }
}