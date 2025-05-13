package com.duoc.gamer.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JwtTokenUtilTest {

    private JwtTokenUtil jwtTokenUtil;
    //private String secretKey = "clave_secreta_prueba";
    private String secretKey = "c2VjcmV0X2tleV9mb3JfdGVzdGluZ19wdXJwb3Nlc19vbmx5";

    private UserDetails userDetails;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {

        jwtTokenUtil = new JwtTokenUtil();
        Field secretKeyField = JwtTokenUtil.class.getDeclaredField("secretKey");
        secretKeyField.setAccessible(true);
        secretKeyField.set(jwtTokenUtil, secretKey);

        userDetails = new User("testuser", "password", Collections.emptyList());

    }

    @Test
    void testGenerateTokenAndExtractUsername() {
        String token = jwtTokenUtil.generateToken(userDetails);
        assertNotNull(token);

        String username = jwtTokenUtil.extractUsername(token);
        assertEquals("testuser", username);
    }

    @Test
    void testExtractExpiration() {
        String token = jwtTokenUtil.generateToken(userDetails);
        Date expiration = jwtTokenUtil.extractExpiration(token);

        assertNotNull(expiration);
        assertTrue(expiration.after(new Date()));
    }

    @Test
    void testValidateTokenSuccess() {
        String token = jwtTokenUtil.generateToken(userDetails);
        boolean isValid = jwtTokenUtil.validateToken(token, userDetails);

        assertTrue(isValid);
    }

    @Test
    void testValidateTokenFailsWithDifferentUser() {
        String token = jwtTokenUtil.generateToken(userDetails);

        UserDetails anotherUser = new User("otroUsuario", "password", Collections.emptyList());
        boolean isValid = jwtTokenUtil.validateToken(token, anotherUser);

        assertFalse(isValid);
    }

    @Test
    void testExpiredTokenShouldBeInvalid() {
        String token = Jwts.builder()
                .setSubject("testuser")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() - 1000)) // ya expirado
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .compact();

        assertThrows(ExpiredJwtException.class, () -> {
            jwtTokenUtil.validateToken(token, userDetails);
        });
    }




}