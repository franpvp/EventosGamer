package com.duoc.gamer.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HomeAdminControllerTest {

    @InjectMocks
    private HomeAdminController homeAdminController;

    @Test
    public void showHomeAdminPage() {

        String resultado = homeAdminController.showHomeAdminPage();

        assertNotNull(resultado);
        assertEquals("admin-home", resultado);

    }
}