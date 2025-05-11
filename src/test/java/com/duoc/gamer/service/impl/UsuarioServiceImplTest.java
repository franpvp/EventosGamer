package com.duoc.gamer.service.impl;

import com.duoc.gamer.dto.UsuarioDTO;
import com.duoc.gamer.entities.UsuarioEntity;
import com.duoc.gamer.exceptions.UsuarioBadRequestException;
import com.duoc.gamer.exceptions.UsuarioNotFoundException;
import com.duoc.gamer.mapper.UsuarioMapper;
import com.duoc.gamer.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    private static final Long ID = 1L;
    private static final Long ID_NEGATIVO = -1L;
    private static final String EMAIL = "test@gmail.com";

    private UsuarioEntity usuarioEntity;

    private UsuarioDTO usuarioDTO;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    void setUp() {
        usuarioEntity = UsuarioEntity.builder()
                .id(ID)
                .build();

        usuarioDTO = UsuarioDTO.builder()
                .id(ID)
                .build();
    }

    @Test
    void getAllUsuarios() {

        when(usuarioRepository.findAll()).thenReturn(List.of(usuarioEntity));
        when(usuarioMapper.usuarioEntityToDto(usuarioEntity)).thenReturn(usuarioDTO);

        List<UsuarioDTO> resultado = usuarioService.getAllUsuarios();

        assertNotNull(resultado);

        verify(usuarioRepository, times(1)).findAll();
        verify(usuarioMapper, times(1)).usuarioEntityToDto(usuarioEntity);

    }

    @Test
    void getUsuarioById() {

        when(usuarioRepository.findById(ID)).thenReturn(Optional.of(usuarioEntity));
        when(usuarioMapper.usuarioEntityToDto(usuarioEntity)).thenReturn(usuarioDTO);

        UsuarioDTO resultado = usuarioService.getUsuarioById(ID);

        assertNotNull(resultado);

        verify(usuarioRepository, times(1)).findById(ID);
        verify(usuarioMapper, times(1)).usuarioEntityToDto(usuarioEntity);

    }

    @Test
    void findByEmailTest() {

        when(usuarioRepository.findByEmail(EMAIL)).thenReturn(Optional.of(usuarioEntity));
        when(usuarioMapper.usuarioEntityToDto(usuarioEntity)).thenReturn(usuarioDTO);

        UsuarioDTO resultado = usuarioService.findByEmail(EMAIL);

        assertNotNull(resultado);

        verify(usuarioRepository).findByEmail(EMAIL);
        verify(usuarioMapper).usuarioEntityToDto(usuarioEntity);

    }

    @Test
    void findByEmailExceptionTest() {

        when(usuarioRepository.findByEmail(EMAIL)).thenReturn(Optional.of(usuarioEntity));

        assertThrows(UsuarioNotFoundException.class,
                () -> usuarioService.findByEmail(EMAIL));

        verify(usuarioRepository).findByEmail(EMAIL);
        verify(usuarioMapper).usuarioEntityToDto(usuarioEntity);

    }

    @Test
    void createUsuarioTest() {

        when(usuarioMapper.usuarioDtoToEntity(usuarioDTO)).thenReturn(usuarioEntity);
        when(usuarioRepository.save(usuarioEntity)).thenReturn(usuarioEntity);
        when(usuarioMapper.usuarioEntityToDto(usuarioEntity)).thenReturn(usuarioDTO);

        UsuarioDTO resultado = usuarioService.createUsuario(usuarioDTO);

        assertNotNull(resultado);

        verify(usuarioMapper, times(1)).usuarioDtoToEntity(usuarioDTO);
        verify(usuarioRepository, times(1)).save(usuarioEntity);
        verify(usuarioMapper, times(1)).usuarioEntityToDto(usuarioEntity);

    }

    @Test
    void modificarUsuarioTest() {

        when(usuarioRepository.findById(ID)).thenReturn(Optional.of(usuarioEntity));
        when(usuarioRepository.save(usuarioEntity)).thenReturn(usuarioEntity);
        when(usuarioMapper.usuarioEntityToDto(usuarioEntity)).thenReturn(usuarioDTO);

        UsuarioDTO resultado = usuarioService.modificarUsuario(usuarioDTO);

        assertNotNull(resultado);

        verify(usuarioRepository, times(1)).findById(ID);
        verify(usuarioRepository, times(1)).save(usuarioEntity);
        verify(usuarioMapper, times(1)).usuarioEntityToDto(usuarioEntity);

    }


    @Test
    void eliminarUsuarioByIdTest() {

        when(usuarioRepository.existsById(ID)).thenReturn(true);
        doNothing().when(usuarioRepository).deleteById(ID);

        usuarioService.eliminarUsuarioById(ID);

        verify(usuarioRepository, times(1)).existsById(ID);
        verify(usuarioRepository, times(1)).deleteById(ID);

    }

    @Test
    void eliminarUsuarioByIdUsuarioBadRequestExceptionTest() {

        UsuarioBadRequestException exception = assertThrows(UsuarioBadRequestException.class,
                () -> usuarioService.eliminarUsuarioById(ID_NEGATIVO));

        assertEquals("El ID del usuario no puede ser negativo: " + ID_NEGATIVO, exception.getMessage());

        verify(usuarioRepository, never()).existsById(anyLong());
        verify(usuarioRepository, never()).deleteById(anyLong());

    }

    @Test
    void eliminarUsuarioByIdUsuarioNotFoundExceptionTest() {

        when(usuarioRepository.existsById(ID)).thenReturn(false);

        UsuarioNotFoundException exception =  assertThrows(UsuarioNotFoundException.class,
                () -> usuarioService.eliminarUsuarioById(ID));

        assertEquals("Usuario no encontrado con ID: " + ID, exception.getMessage());

        verify(usuarioRepository, times(1)).existsById(ID);
        verify(usuarioRepository, never()).deleteById(anyLong());

    }
}