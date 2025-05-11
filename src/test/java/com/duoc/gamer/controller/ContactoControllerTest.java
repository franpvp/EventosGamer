package com.duoc.gamer.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ContactoControllerTest {

    private static final String NOMBRE = "NOMBRE";
    private static final String EMAIL = "test@gmail.com";
    private static final String MENSAJE = "MENSAJE";

    @InjectMocks
    private ContactoController contactoController;

    @Test
    void mostrarFormulario() {

        String resultado = contactoController.mostrarFormulario();

        assertNotNull(resultado);

        assertEquals("contacto", resultado);

    }

    @Test
    void procesarFormulario() {

        String resultado = contactoController.procesarFormulario(NOMBRE, EMAIL, MENSAJE);

        assertEquals("redirect:/contacto?success", resultado);

    }
}