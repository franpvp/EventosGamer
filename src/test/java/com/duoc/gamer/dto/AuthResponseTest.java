package com.duoc.gamer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class AuthResponseTest {

    private AuthResponse response;

    @BeforeEach
    void setUp() {
        response = new AuthResponse("token");
    }

    @Test
    public void authResponseTest() {

        String getToken = response.getToken();
        response.setToken("token");

        assertNotNull(getToken);
        assertEquals("token", response.getToken());

    }

    @Test
    public void authResponseNoArgsTest() {

        AuthResponse res = new AuthResponse();

        assertNotNull(res);

    }
}