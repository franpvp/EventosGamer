package com.duoc.gamer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthRequestTest {

    private static final String EMAIL = "test@gmail.com";
    private static final String PASSWORD = "password123";

    private AuthRequest authRequest;

    @BeforeEach
    void setUp() {

        authRequest = new AuthRequest(EMAIL, PASSWORD);

    }


    @Test
    public void authRequestTest() {

        String getEmail = authRequest.getEmail();
        authRequest.setEmail(EMAIL);

        String getPassword = authRequest.getPassword();
        authRequest.setPassword(PASSWORD);

        assertNotNull(authRequest);
        assertNotNull(getEmail);
        assertNotNull(getPassword);
        assertEquals(EMAIL, authRequest.getEmail());
        assertEquals(PASSWORD, authRequest.getPassword());

    }

    @Test
    public void authRequestNoArgsTest() {

        AuthRequest res = new AuthRequest();

        assertNotNull(res);

    }
}