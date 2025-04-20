package com.duoc.gamer.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthDTOTest {

    private static final String EMAIL = "test@gmail.com";
    private static final String PASSWORD = "password";

    private AuthDTO authDTO;

    @BeforeEach
    void setUp() {

        authDTO = AuthDTO.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .build();
    }

    @Test
    public void authDtoTest() {

        assertNotNull(authDTO);
        assertEquals(EMAIL, authDTO.getEmail());
        assertEquals(PASSWORD, authDTO.getPassword());

    }

    @Test
    public void authDtoSetterTest() {
        authDTO.setEmail(EMAIL);
        authDTO.setPassword(PASSWORD);
    }

    @Test
    public void authDtoNoArgsTest() {
        AuthDTO authDTO1 = new AuthDTO();

        assertNotNull(authDTO1);
    }


}