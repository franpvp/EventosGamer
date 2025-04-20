package com.duoc.gamer.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RecuperarContrasenaControllerTest {

    @InjectMocks
    private RecuperarContrasenaController recuperarContrasenaController;

    @BeforeEach
    void setUp() {
    }

    @Test
    void showRecuperarContrasenaPage() {

        String resultado = recuperarContrasenaController.showRecuperarContrasenaPage();

        assertNotNull(resultado);
        assertEquals("recuperar-contrasena", resultado);


    }
}